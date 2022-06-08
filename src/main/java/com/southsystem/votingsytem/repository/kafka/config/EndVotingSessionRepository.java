package com.southsystem.votingsytem.repository.kafka.config;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import org.springframework.stereotype.Repository;

@Repository
public class EndVotingSessionRepository extends AbstractRepositoryKafka<SubjectVoting> {

    @Override
    protected String getTopicName() {
        return "advice-topic";
    }
}
