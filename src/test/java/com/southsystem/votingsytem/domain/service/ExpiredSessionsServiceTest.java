package com.southsystem.votingsytem.domain.service;

import com.southsystem.votingsytem.domain.entity.Result;
import com.southsystem.votingsytem.domain.entity.SessionVoting;
import com.southsystem.votingsytem.domain.exception.SubjectVotingNotFoundException;
import com.southsystem.votingsytem.domain.repository.SessionVotingNotifyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class ExpiredSessionsServiceTest {
    @Mock
    private SessionVotingService sessionVotingService;
    @Mock
    private CalculateResultsService calculateResultsService;
    @Mock
    private SessionVotingNotifyRepository notifyRepository;

    private ExpiredSessionsService service;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        this.service = new ExpiredSessionsService(sessionVotingService, calculateResultsService, notifyRepository);
    }

    @Test
    public void validate() throws SubjectVotingNotFoundException {
        Mockito.when(this.sessionVotingService.findExpiredSessions(Mockito.any()))
                .thenReturn(List.of(UUID.randomUUID().toString()));

        Mockito.when(this.sessionVotingService.findOne(Mockito.any())).thenReturn(SessionVoting.builder().id(UUID.randomUUID().toString()).build());
        Mockito.when(this.calculateResultsService.calculate(Mockito.any()))
                .thenReturn(Result.builder().build());

        this.service.execute();
        Mockito.verify(this.calculateResultsService, Mockito.times(1)).calculate(Mockito.any());
    }

    @Test
    public void validateException() throws SubjectVotingNotFoundException {
        Mockito.when(this.sessionVotingService.findExpiredSessions(Mockito.any()))
                .thenReturn(List.of(UUID.randomUUID().toString()));

        Mockito.when(this.sessionVotingService.findOne(Mockito.any()))
                .thenThrow(SubjectVotingNotFoundException.class);

        this.service.execute();

        Mockito.verify(this.sessionVotingService, Mockito.times(1)).findExpiredSessions(Mockito.any());

    }
}
