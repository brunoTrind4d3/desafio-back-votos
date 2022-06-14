package com.southsystem.votingsytem;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
class VotingSytemApplicationTests {


    @Test
    public void testInstantiation() {
        var application = new VotingSytemApplication();
        assertTrue(application instanceof VotingSytemApplication, "must be VotingSystemApplication");
    }
}
