package com.southsystem.votingsytem.domain.service;

import com.southsystem.votingsytem.domain.entity.Vote;
import com.southsystem.votingsytem.domain.exception.InvalidTaxIdException;
import com.southsystem.votingsytem.domain.exception.SubjectVotingClosedException;
import com.southsystem.votingsytem.domain.exception.SubjectVotingNotFoundException;
import com.southsystem.votingsytem.domain.exception.TaxIdAlreadyVotedException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VoteService {

    private final SubjectVotingService votingService;

    public boolean create(String subjectId, Vote vote) throws SubjectVotingNotFoundException, SubjectVotingClosedException, InvalidTaxIdException, TaxIdAlreadyVotedException {
       var createdVote = this.votingService.addVote(subjectId, vote);
       return createdVote != null;
    }
}
