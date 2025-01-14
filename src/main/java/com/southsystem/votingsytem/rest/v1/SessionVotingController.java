package com.southsystem.votingsytem.rest.v1;

import com.southsystem.votingsytem.domain.entity.BusinessError;
import com.southsystem.votingsytem.domain.entity.SessionVoting;
import com.southsystem.votingsytem.domain.exception.SubjectVotingNotFoundException;
import com.southsystem.votingsytem.domain.service.SessionVotingService;
import com.southsystem.votingsytem.rest.mapper.SessionVotingMapper;
import com.southsystem.votingsytem.rest.model.CreateSessionVoting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1/session-voting")
public class SessionVotingController {

    @Autowired
    SessionVotingService service;

    @GetMapping("/{id}")
    public SessionVoting findOne(@PathVariable String id) throws SubjectVotingNotFoundException {
        return this.service.findOne(id);
    }

    @PostMapping()
    public SessionVoting create(@Valid @RequestBody CreateSessionVoting subject) {
        return this.service.create(SessionVotingMapper.from(subject));
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
