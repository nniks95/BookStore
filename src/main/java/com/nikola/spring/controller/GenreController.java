package com.nikola.spring.controller;

import com.nikola.spring.dto.GenreDto;
import com.nikola.spring.exceptions.DataNotValidatedException;
import com.nikola.spring.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/genres")
public class GenreController {
    @Autowired private GenreService genreService;

    @PostMapping(value = "/addGenre")
    public ResponseEntity<GenreDto> addGenre(@RequestBody @Validated GenreDto genreDto, Errors errors){
        if(errors.hasErrors()) {
            throw  new DataNotValidatedException(new Error("Validation not passed."));
        }
        return new ResponseEntity<>(genreService.addGenre(genreDto), HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteGenre/{genreId}")
    public ResponseEntity<String> deleteGenreById(@PathVariable("genreId") Integer genreId){
        String message= "Genre is deleted.";
        genreService.deleteGenre(genreId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
