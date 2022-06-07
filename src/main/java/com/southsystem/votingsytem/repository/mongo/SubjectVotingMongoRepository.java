package com.southsystem.votingsytem.repository.mongo;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectVotingMongoRepository extends MongoRepository<SubjectVoting, String> {
}
