package com.nikola.spring.service;

import com.nikola.spring.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto addBook(BookDto bookDto);
    List<BookDto> listAllBooks();
    BookDto getBookById(Integer bookId);
    BookDto updateBookById(Integer bookId, BookDto bookDto);
    void deleteBook(Integer bookId);
    List<BookDto> listAllByGenreId(Integer genreId);
    List<BookDto> listAllByAuthorId(Integer authorId);
    List<BookDto> listAllByPublisherId(Integer publisherId);


}
