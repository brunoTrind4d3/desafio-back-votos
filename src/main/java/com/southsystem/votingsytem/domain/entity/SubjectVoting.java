package com.southsystem.votingsytem.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectVoting {
    @Id
    private String id;
    private String description;
    private Integer duration;
    private boolean isClosed;

    private Result results;
    private List<Vote> votes;

    private Date createdAt;
    private Date finishedAt;
}
