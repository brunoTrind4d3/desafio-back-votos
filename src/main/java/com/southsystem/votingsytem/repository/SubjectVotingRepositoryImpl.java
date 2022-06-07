package com.southsystem.votingsytem.repository;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import com.southsystem.votingsytem.domain.repository.SubjectVotingRepository;
import com.southsystem.votingsytem.repository.mongo.SubjectVotingMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SubjectVotingRepositoryImpl implements SubjectVotingRepository {

    @Autowired
    SubjectVotingMongoRepository repository;

    @Override
    public SubjectVoting create(SubjectVoting subject) {
        return this.repository.insert(subject);
    }

    @Override
    public Optional<SubjectVoting> findOne(String id) {
        return this.repository.findById(id);
    }

    @Override
    public SubjectVoting addVote(SubjectVoting subject) {
        return this.repository.save(subject);
    }
}
