package com.southsystem.votingsytem.repository;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import com.southsystem.votingsytem.domain.repository.SessionVotingNotifyRepository;
import com.southsystem.votingsytem.repository.rabbitmq.RabbitMQMessageMapper;
import com.southsystem.votingsytem.repository.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SessionVotingNotifyRepositoryImpl implements SessionVotingNotifyRepository {

    @Autowired
    RabbitMQSender sender;

    @Override
    public void notify(SessionVoting subject) {
        this.sender.send(RabbitMQMessageMapper.from(subject));
    }
}
