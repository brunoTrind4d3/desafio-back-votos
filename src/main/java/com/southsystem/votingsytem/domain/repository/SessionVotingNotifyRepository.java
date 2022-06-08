package com.southsystem.votingsytem.domain.repository;

import com.southsystem.votingsytem.domain.entity.SessionVoting;

public interface SessionVotingNotifyRepository {

    void notify(SessionVoting subject);
}
