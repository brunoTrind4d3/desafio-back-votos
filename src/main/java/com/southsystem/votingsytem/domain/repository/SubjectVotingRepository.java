package com.southsystem.votingsytem.domain.repository;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SubjectVotingRepository {
    SubjectVoting create(SubjectVoting subject);
    void update(SubjectVoting subject);
    Optional<SubjectVoting> findOne(String id);
    SubjectVoting addVote(SubjectVoting subject);

    List<String> findExpiredSessions(Date expirationDate);
}
