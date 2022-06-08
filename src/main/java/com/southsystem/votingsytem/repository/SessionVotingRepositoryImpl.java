package com.southsystem.votingsytem.repository;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import com.southsystem.votingsytem.domain.repository.SessionVotingRepository;
import com.southsystem.votingsytem.repository.mongo.SessionVotingMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class SessionVotingRepositoryImpl implements SessionVotingRepository {

    @Autowired
    SessionVotingMongoRepository repository;

    @Override
    public SessionVoting create(SessionVoting subject) {
        return this.repository.insert(subject);
    }

    @Override
    public void update(SessionVoting subject) {
        this.repository.save(subject);
    }

    @Override
    public Optional<SessionVoting> findOne(String id) {
        return this.repository.findById(id);
    }

    @Override
    public SessionVoting addVote(SessionVoting subject) {
        return this.repository.save(subject);
    }

    @Override
    public List<String> findExpiredSessions(Date expirationDate) {
        var result = this.repository.findByIsClosedAndFinishedAtLessThan(false, expirationDate);
        if(result.isEmpty()){
            return new ArrayList<>();
        }
        var ids = result.stream().map(SessionVoting::getId).collect(Collectors.toList());
        log.info("EXPIRED SESSIONS IDs: {}", ids);
        return ids;
    }
}
