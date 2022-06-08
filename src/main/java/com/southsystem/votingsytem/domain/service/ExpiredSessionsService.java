package com.southsystem.votingsytem.domain.service;

import com.southsystem.votingsytem.domain.exception.SubjectVotingNotFoundException;
import com.southsystem.votingsytem.domain.repository.SessionVotingNotifyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@AllArgsConstructor
@Slf4j
public class ExpiredSessionsService {

    private final SubjectVotingService service;
    private final CalculateResultsService calculateResultsService;
    private final SessionVotingNotifyRepository notifyRepository;

    public void execute(){
        var expiredSessions = this.service.findExpiredSessions(new Date());
        expiredSessions.forEach(id -> {
            try {
                var subject = this.service.findOne(id);
                var result = this.calculateResultsService.calculate(subject);

                subject.setResults(result);
                subject.setClosed(true);
                this.service.update(subject);
                this.notifyRepository.notify(subject);
            }catch (SubjectVotingNotFoundException ex){
                log.error(ex.getMessage());
            }
        });
    }
}
