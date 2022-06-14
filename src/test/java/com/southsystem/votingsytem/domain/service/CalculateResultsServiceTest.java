package com.southsystem.votingsytem.domain.service;

import com.southsystem.votingsytem.domain.entity.Result;
import com.southsystem.votingsytem.domain.entity.SessionVoting;
import com.southsystem.votingsytem.domain.entity.Vote;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CalculateResultsServiceTest {

    private CalculateResultsService service;

    @Before
    public void setup(){
        this.service = new CalculateResultsService();
    }

    @Test
    public void calculate(){
        var vote1 = Vote.builder().taxId("70502393130").candidate(true).build();
        var vote2=  Vote.builder().taxId("70502393130").candidate(true).build();
        var vote3 = Vote.builder().taxId("70502393130").candidate(false).build();
        var sessionVoting = SessionVoting.builder()
                .votes(List.of(vote1, vote2, vote3))
                .build();
       var result = this.service.calculate(sessionVoting);
        Assert.assertNotNull(result);
    }
}
