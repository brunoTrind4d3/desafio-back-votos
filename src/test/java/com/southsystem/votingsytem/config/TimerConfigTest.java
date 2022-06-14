package com.southsystem.votingsytem.config;

import com.southsystem.votingsytem.domain.service.ExpiredSessionsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimerConfigTest {

    @Mock
    private ExpiredSessionsService expiredSessionsService;

    @InjectMocks
    private TimerConfig timerConfig;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void interval(){
        this.timerConfig.onInterval();
        Mockito.verify(expiredSessionsService, Mockito.times(1)).execute();
    }
}
