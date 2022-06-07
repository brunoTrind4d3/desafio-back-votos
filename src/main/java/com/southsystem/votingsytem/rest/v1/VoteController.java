package com.southsystem.votingsytem.rest.v1;


import com.southsystem.votingsytem.domain.entity.BusinessError;
import com.southsystem.votingsytem.domain.entity.Vote;
import com.southsystem.votingsytem.domain.exception.InvalidTaxIdException;
import com.southsystem.votingsytem.domain.exception.SubjectVotingClosedException;
import com.southsystem.votingsytem.domain.exception.SubjectVotingNotFoundException;
import com.southsystem.votingsytem.domain.exception.TaxIdAlreadyVotedException;
import com.southsystem.votingsytem.domain.service.VoteService;
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
@RequestMapping("api/v1/vote")
public class VoteController {

    @Autowired
    VoteService service;

    @PostMapping("/{subjectId}")
    public ResponseEntity<HttpStatus> create(@PathVariable String subjectId, @Valid @RequestBody Vote body) throws SubjectVotingNotFoundException,
            SubjectVotingClosedException, InvalidTaxIdException, TaxIdAlreadyVotedException {
        var created = this.service.create(subjectId, body);
        return created ? new ResponseEntity<>(HttpStatus.CREATED) : null;
    }

    @ExceptionHandler({SubjectVotingNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BusinessError> handleNotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(BusinessError.builder().errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.NOT_FOUND.value()).build());
    }

    @ExceptionHandler({SubjectVotingClosedException.class, InvalidTaxIdException.class, TaxIdAlreadyVotedException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<BusinessError> handleBusinessException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON)
                .body(BusinessError.builder().errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.UNPROCESSABLE_ENTITY.value()).build());
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