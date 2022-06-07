package com.southsystem.votingsytem.repository;

import com.mongodb.BasicDBObject;
import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import com.southsystem.votingsytem.domain.repository.SubjectVotingRepository;
import com.southsystem.votingsytem.repository.mongo.SubjectVotingMongoRepository;
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
public class SubjectVotingRepositoryImpl implements SubjectVotingRepository {

    @Autowired
    SubjectVotingMongoRepository repository;

    @Override
    public SubjectVoting create(SubjectVoting subject) {
        return this.repository.insert(subject);
    }

    @Override
    public void update(SubjectVoting subject) {
        this.repository.save(subject);
    }

    @Override
    public Optional<SubjectVoting> findOne(String id) {
        return this.repository.findById(id);
    }

    @Override
    public SubjectVoting addVote(SubjectVoting subject) {
        return this.repository.save(subject);
    }

    @Override
    public List<String> findExpiredSessions(Date expirationDate) {
        var result = this.repository.findByIsClosedAndFinishedAtLessThan(false, expirationDate);
        if(result.isEmpty()){
            return new ArrayList<>();
        }
        var ids = result.stream().map(SubjectVoting::getId).collect(Collectors.toList());
        log.info("EXPIRED SESSIONS IDs: {}", ids);
        return ids;
    }
}
