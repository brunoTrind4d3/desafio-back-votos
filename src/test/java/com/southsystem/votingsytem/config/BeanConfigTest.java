package com.southsystem.votingsytem.config;

import com.southsystem.votingsytem.domain.repository.EligibilityToVoteRepository;
import com.southsystem.votingsytem.domain.repository.SessionVotingNotifyRepository;
import com.southsystem.votingsytem.domain.repository.SessionVotingRepository;
import com.southsystem.votingsytem.domain.service.CalculateResultsService;
import com.southsystem.votingsytem.domain.service.ExpiredSessionsService;
import com.southsystem.votingsytem.domain.service.SessionVotingService;
import com.southsystem.votingsytem.domain.service.VoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BeanConfigTest {

    @Mock
    private CalculateResultsService calculateResultsService;
    @Mock
    private SessionVotingNotifyRepository sessionVotingNotifyRepository;
    @Mock
    private SessionVotingRepository sessionVotingRepository;
    @Mock
    private EligibilityToVoteRepository eligibilityToVoteRepository;
    @Mock
    private SessionVotingService sessionVotingService;
    private BeanConfig beanConfig;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        beanConfig = new BeanConfig();
    }

    @Test
    public void testSessionVotingService(){
        var application = this.beanConfig.subjectVotingService(this.sessionVotingRepository, this.eligibilityToVoteRepository);
        Assert.assertTrue("must be SessionVotingService", application instanceof SessionVotingService);
    }

    @Test
    public void testVoteService(){
        var application = this.beanConfig.voteService(this.sessionVotingService);
        Assert.assertTrue("must be VoteService", application instanceof VoteService);
    }
    @Test
    public void testExpiredSessionsService(){
        var application = this.beanConfig.expiredSessionsService(this.sessionVotingService, this.calculateResultsService,
                this.sessionVotingNotifyRepository);
        Assert.assertTrue("must be ExpiredSessionsService", application instanceof ExpiredSessionsService);
    }

    @Test
    public void testCalculateResultsService(){
        var application = this.beanConfig.calculateResultsService();
        Assert.assertTrue("must be CalculateResultsService", application instanceof CalculateResultsService);
    }

}
