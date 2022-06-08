package com.southsystem.votingsytem.config;

import com.southsystem.votingsytem.domain.repository.EligibilityToVoteRepository;
import com.southsystem.votingsytem.domain.repository.SessionVotingNotifyRepository;
import com.southsystem.votingsytem.domain.repository.SubjectVotingRepository;
import com.southsystem.votingsytem.domain.service.CalculateResultsService;
import com.southsystem.votingsytem.domain.service.ExpiredSessionsService;
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

    @Bean
    ExpiredSessionsService expiredSessionsService(SubjectVotingService service,
                                                  CalculateResultsService calculateResultsService,
                                                  SessionVotingNotifyRepository sessionVotingNotifyRepository){
        return new ExpiredSessionsService(service, calculateResultsService, sessionVotingNotifyRepository);
    }

    @Bean
    CalculateResultsService calculateResultsService(){
        return new CalculateResultsService();
    }
}
