package com.southsystem.votingsytem.domain.repository;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;

import java.util.Optional;

public interface SubjectVotingRepository {
    SubjectVoting create(SubjectVoting subject);
    Optional<SubjectVoting> findOne(String id);
    void addVote(SubjectVoting subject);
}
