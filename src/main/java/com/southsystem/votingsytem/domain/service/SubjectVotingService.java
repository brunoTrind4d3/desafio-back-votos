package com.southsystem.votingsytem.domain.service;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import com.southsystem.votingsytem.domain.entity.Vote;
import com.southsystem.votingsytem.domain.exception.InvalidTaxIdException;
import com.southsystem.votingsytem.domain.exception.SubjectVotingClosedException;
import com.southsystem.votingsytem.domain.exception.SubjectVotingNotFoundException;
import com.southsystem.votingsytem.domain.exception.TaxIdAlreadyVotedException;
import com.southsystem.votingsytem.domain.repository.EligibilityToVoteRepository;
import com.southsystem.votingsytem.domain.repository.SubjectVotingRepository;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class SubjectVotingService {

    private final SubjectVotingRepository repository;
    private final EligibilityToVoteRepository eligibilityToVoteRepository;

    public SubjectVoting create(SubjectVoting subject) {
        if (subject.getId() == null) {
            var createdAt = new Date();
            subject.setId(UUID.randomUUID().toString());
            subject.setCreatedAt(createdAt);
            subject.setClosed(false);
            subject.setFinishedAt(getExpirationDate(subject.getDuration(), createdAt));
        }
        return this.repository.create(subject);
    }

    public SubjectVoting findOne(String id) throws SubjectVotingNotFoundException {
        var result = this.repository.findOne(id);
        if (result.isEmpty()) {
            throw new SubjectVotingNotFoundException("Subject voting does not exists");
        }
        return result.get();
    }

    public void update(SubjectVoting subject) {
        this.repository.update(subject);
    }

    public SubjectVoting addVote(String subjectId, Vote vote) throws SubjectVotingNotFoundException, SubjectVotingClosedException, InvalidTaxIdException, TaxIdAlreadyVotedException {
        var result = this.findOne(subjectId);
        if (result.isClosed()) {
            throw new SubjectVotingClosedException("Voting subject is closed");
        }
        var taxIdVoted = Optional.ofNullable(result.getVotes()).orElse(new ArrayList<>()).stream()
                .filter(f -> f.getTaxId().equals(vote.getTaxId())).findAny();
        if (taxIdVoted.isEmpty()) {
            if (!this.eligibilityToVoteRepository.isEligible(vote.getTaxId())) {
                throw new InvalidTaxIdException("You can't vote in this session");
            }
            if (result.getVotes() == null) {
                result.setVotes(List.of(vote));
            } else {
                result.getVotes().add(vote);
            }
            return this.repository.addVote(result);
        }
        throw new TaxIdAlreadyVotedException("You already voted");
    }

    private Date getExpirationDate(Integer duration, Date createdAt) {
        var newDate = Calendar.getInstance();
        newDate.setTime(createdAt);
        newDate.add(Calendar.MINUTE, Objects.requireNonNullElse(duration, 1));
        return newDate.getTime();
    }

    public List<String> findExpiredSessions(Date expirationDate) {
        return this.repository.findExpiredSessions(expirationDate);
    }
}
