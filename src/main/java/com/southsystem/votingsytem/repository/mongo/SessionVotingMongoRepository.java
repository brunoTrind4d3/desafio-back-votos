package com.southsystem.votingsytem.repository.mongo;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface SessionVotingMongoRepository extends MongoRepository<SessionVoting, String> {

    List<SessionVoting> findByIsClosedAndFinishedAtLessThan(boolean isClosed, Date expirationDate);
}
