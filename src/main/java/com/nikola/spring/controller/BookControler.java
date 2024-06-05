package com.nikola.spring.controller;

import com.nikola.spring.dto.BookDto;
import com.nikola.spring.exceptions.DataNotValidatedException;
import com.nikola.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/books")
public class BookControler {
    @Autowired private BookService bookService;

    @PostMapping(value = "/addBook")
    public ResponseEntity<BookDto> addBook(@RequestBody @Validated BookDto bookDto, Errors errors){
        if(errors.hasErrors()){
        throw new DataNotValidatedException(new Error("Validation not passed."));
        }
        return new ResponseEntity<>(bookService.addBook(bookDto),HttpStatus.OK);
    }

    @GetMapping(value = "/allBooks")
    public ResponseEntity<List<BookDto>> listAllBooks(){
        return new ResponseEntity<>(bookService.listAllBooks(),HttpStatus.OK);
    }

    @GetMapping(value = "/bookDetails/{bookId}")
    public ResponseEntity<BookDto> getBookDetails(@PathVariable("bookId") Integer bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PutMapping(value = "/updateBookDetails/{bookId}")
    public ResponseEntity<BookDto> updateBookById(@RequestBody @Validated BookDto bookDto, Errors errors, @PathVariable("bookId") Integer bookId){
        if(errors.hasErrors()){
            throw new DataNotValidatedException(new Error("Validation not passed."));
        }
        return new ResponseEntity<>(bookService.updateBookById(bookId,bookDto),HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteBook/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable("bookId") Integer bookId){
        String message = "Book has been deleted.";
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}


