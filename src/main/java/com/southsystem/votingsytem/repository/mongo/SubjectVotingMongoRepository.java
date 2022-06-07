package com.southsystem.votingsytem.repository.mongo;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface SubjectVotingMongoRepository extends MongoRepository<SubjectVoting, String> {

    List<SubjectVoting> findByIsClosedAndFinishedAtLessThan(boolean isClosed, Date expirationDate);
}
