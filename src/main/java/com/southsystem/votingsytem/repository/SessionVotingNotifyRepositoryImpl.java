package com.southsystem.votingsytem.repository;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import com.southsystem.votingsytem.domain.repository.SessionVotingNotifyRepository;
import com.southsystem.votingsytem.repository.kafka.config.EndVotingSessionRepository;
import com.southsystem.votingsytem.repository.kafka.mapper.KafkaMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SessionVotingNotifyRepositoryImpl implements SessionVotingNotifyRepository {

    @Autowired
    private EndVotingSessionRepository kafkaNotification;

    @Override
    public void notify(SubjectVoting subject) {
        this.kafkaNotification.notify(KafkaMessageMapper.from(subject));
    }
}
