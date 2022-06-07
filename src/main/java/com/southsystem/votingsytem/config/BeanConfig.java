package com.southsystem.votingsytem.config;

import com.southsystem.votingsytem.domain.repository.EligibilityToVoteRepository;
import com.southsystem.votingsytem.domain.repository.SubjectVotingRepository;
import com.southsystem.votingsytem.domain.service.SubjectVotingService;
import com.southsystem.votingsytem.domain.service.VoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    SubjectVotingService subjectVotingService(SubjectVotingRepository repository,
                                              EligibilityToVoteRepository eligibilityToVoteRepository) {
        return new SubjectVotingService(repository, eligibilityToVoteRepository);
    }

    @Bean
    VoteService voteService(SubjectVotingService service) {
        return new VoteService(service);
    }
}
