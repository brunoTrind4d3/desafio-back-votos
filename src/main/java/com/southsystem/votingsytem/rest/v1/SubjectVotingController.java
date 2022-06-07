package com.southsystem.votingsytem.rest.v1;

import com.southsystem.votingsytem.domain.entity.BusinessError;
import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import com.southsystem.votingsytem.domain.exception.SubjectVotingNotFoundException;
import com.southsystem.votingsytem.domain.service.SubjectVotingService;
import com.southsystem.votingsytem.rest.mapper.SubjectVotingMapper;
import com.southsystem.votingsytem.rest.model.CreateSubjectVoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/subject")
public class SubjectVotingController {

    @Autowired
    SubjectVotingService service;

    @GetMapping("/{id}")
    public SubjectVoting findOne(@PathVariable String id) throws SubjectVotingNotFoundException {
        return this.service.findOne(id);
    }

    @PostMapping()
    public SubjectVoting create(@RequestBody CreateSubjectVoting subject) {
        return this.service.create(SubjectVotingMapper.from(subject));
    }

    @ExceptionHandler({SubjectVotingNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BusinessError> handleNotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BusinessError.builder()
                        .errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }


}
