package com.southsystem.votingsytem.domain.repository;

import com.southsystem.votingsytem.domain.entity.SessionVoting;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SessionVotingRepository {
    SessionVoting create(SessionVoting subject);
    void update(SessionVoting subject);
    Optional<SessionVoting> findOne(String id);
    SessionVoting addVote(SessionVoting subject);

    List<String> findExpiredSessions(Date expirationDate);
}
