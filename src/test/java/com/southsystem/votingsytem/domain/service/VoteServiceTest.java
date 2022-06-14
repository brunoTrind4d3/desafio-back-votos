package com.southsystem.votingsytem.domain.service;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import com.southsystem.votingsytem.domain.entity.Vote;
import com.southsystem.votingsytem.domain.exception.InvalidTaxIdException;
import com.southsystem.votingsytem.domain.exception.SubjectVotingClosedException;
import com.southsystem.votingsytem.domain.exception.SubjectVotingNotFoundException;
import com.southsystem.votingsytem.domain.exception.TaxIdAlreadyVotedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @Mock
    private SessionVotingService sessionVotingService;

    private VoteService voteService;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        this.voteService = new VoteService(this.sessionVotingService);
    }

    @Test
    public void testVoteOk() throws SubjectVotingNotFoundException, SubjectVotingClosedException, InvalidTaxIdException, TaxIdAlreadyVotedException {

        var id =UUID.randomUUID().toString();
        Mockito.when(this.sessionVotingService
                .addVote(Mockito.anyString(), Mockito.any()))
                .thenReturn(SessionVoting.builder().id(id).build());

        var result =this.voteService.create(id, Vote.builder().candidate(true).taxId("70502393130").build());
        Assert.assertTrue(result);

    }

    @Test(expected = TaxIdAlreadyVotedException.class)
    public void testVoteExceptionTaxId() throws SubjectVotingNotFoundException, SubjectVotingClosedException, InvalidTaxIdException, TaxIdAlreadyVotedException {

        var id =UUID.randomUUID().toString();
        Mockito.when(this.sessionVotingService
                        .addVote(Mockito.anyString(), Mockito.any()))
                .thenThrow(TaxIdAlreadyVotedException.class);

        var result =this.voteService.create(id, Vote.builder().candidate(true).taxId("70502393130").build());
    }

    @Test(expected = SubjectVotingNotFoundException.class)
    public void testVoteSubjectVotingNotFoundException() throws SubjectVotingNotFoundException, SubjectVotingClosedException, InvalidTaxIdException, TaxIdAlreadyVotedException {

        var id =UUID.randomUUID().toString();
        Mockito.when(this.sessionVotingService
                        .addVote(Mockito.anyString(), Mockito.any()))
                .thenThrow(SubjectVotingNotFoundException.class);

        var result =this.voteService.create(id, Vote.builder().candidate(true).taxId("70502393130").build());
    }

}
