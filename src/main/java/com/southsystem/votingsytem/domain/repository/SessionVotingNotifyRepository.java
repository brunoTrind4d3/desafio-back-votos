package com.southsystem.votingsytem.domain.repository;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;

public interface SessionVotingNotifyRepository {

    void notify(SubjectVoting subject);
}
