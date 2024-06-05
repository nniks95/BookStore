package com.nikola.spring.controller;

import com.nikola.spring.dto.PublisherDto;
import com.nikola.spring.exceptions.DataNotValidatedException;
import com.nikola.spring.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @PostMapping(value = "/addPublisher")
    public ResponseEntity<PublisherDto> addPublisher(@RequestBody @Validated PublisherDto publisherDto, Errors errors){
        if(errors.hasErrors()){
            throw new DataNotValidatedException(new Error("Validation not passed"));
        }
        return new ResponseEntity<>(publisherService.addPublisher(publisherDto), HttpStatus.OK);
    }
}
