package com.nikola.spring.controller;

import com.nikola.spring.dto.AuthorDto;
import com.nikola.spring.dto.PublisherDto;
import com.nikola.spring.exceptions.DataNotValidatedException;
import com.nikola.spring.service.AuthorService;
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
@RequestMapping(value = "/api/authors")
public class AuthorController {
    @Autowired private AuthorService authorService;

    @PostMapping(value = "/addAuthor")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody @Validated AuthorDto authorDto, Errors errors){
        if(errors.hasErrors()){
            throw new DataNotValidatedException(new Error("Validation not passed"));
        }
        return new ResponseEntity<>(authorService.addAuthor(authorDto), HttpStatus.OK);
    }
}
