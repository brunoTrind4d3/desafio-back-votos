package com.southsystem.votingsytem.config;

import com.southsystem.votingsytem.domain.repository.EligibilityToVoteRepository;
import com.southsystem.votingsytem.domain.repository.SessionVotingNotifyRepository;
import com.southsystem.votingsytem.domain.repository.SessionVotingRepository;
import com.southsystem.votingsytem.domain.service.CalculateResultsService;
import com.southsystem.votingsytem.domain.service.ExpiredSessionsService;
import com.southsystem.votingsytem.domain.service.SessionVotingService;
import com.southsystem.votingsytem.domain.service.VoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    SessionVotingService subjectVotingService(SessionVotingRepository repository,
                                              EligibilityToVoteRepository eligibilityToVoteRepository) {
        return new SessionVotingService(repository, eligibilityToVoteRepository);
    }

    @Bean
    VoteService voteService(SessionVotingService service) {
        return new VoteService(service);
    }

    @Bean
    ExpiredSessionsService expiredSessionsService(SessionVotingService service,
                                                  CalculateResultsService calculateResultsService,
                                                  SessionVotingNotifyRepository sessionVotingNotifyRepository){
        return new ExpiredSessionsService(service, calculateResultsService, sessionVotingNotifyRepository);
    }

    @Bean
    CalculateResultsService calculateResultsService(){
        return new CalculateResultsService();
    }
}
