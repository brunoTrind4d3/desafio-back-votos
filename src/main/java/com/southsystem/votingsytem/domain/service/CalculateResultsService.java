package com.southsystem.votingsytem.domain.service;

import com.southsystem.votingsytem.domain.entity.Result;
import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import com.southsystem.votingsytem.domain.entity.Vote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Optional;

public class CalculateResultsService {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);


    public Result calculate(SubjectVoting subject) {
        var votes = Optional.ofNullable(subject.getVotes()).orElse(new ArrayList<>());
        var yesVotes = (int) votes.stream()
                .filter(Vote::getCandidate).count();

        var noVotes = votes.size() - yesVotes;

        var yesVotesPercent = getPercentValue(BigDecimal.valueOf(votes.size()), BigDecimal.valueOf(yesVotes));
        var noVotesPercent = getPercentValue(BigDecimal.valueOf(votes.size()), BigDecimal.valueOf(noVotes));

        return Result.builder()
                .yesVotes(yesVotesPercent.toString())
                .noVotes(noVotesPercent.toString())
                .build();

    }

    private BigDecimal getPercentValue(BigDecimal totalSize, BigDecimal votes) {
        return votes.multiply(ONE_HUNDRED).divide(totalSize, RoundingMode.HALF_UP);
    }

}
