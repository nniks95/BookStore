package com.nikola.spring.service.impl;

import com.nikola.spring.converter.TempConverter;
import com.nikola.spring.dto.BookDto;
import com.nikola.spring.entities.BookEntity;
import com.nikola.spring.exceptions.InstanceUndefinedException;
import com.nikola.spring.repositories.AuthorRepository;
import com.nikola.spring.repositories.BookRepository;
import com.nikola.spring.repositories.GenreRepository;
import com.nikola.spring.repositories.PublisherRepository;
import com.nikola.spring.service.BookService;
import com.nikola.spring.utils.RandomStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    @Autowired private TempConverter tempConverter;
    @Autowired private BookRepository bookRepository;
    @Autowired private GenreRepository genreRepository;
    @Autowired private PublisherRepository publisherRepository;
    @Autowired private AuthorRepository authorRepository;
    @Autowired private RandomStringUtil randomStringUtil;

    @Override
    public BookDto addBook(BookDto bookDto) {
        String isbn = randomStringUtil.getAlphaNumericString(13);
        bookDto.setISBN(isbn);
        bookDto.setAvailable((byte) 1);
        BookEntity book = tempConverter.dtoToEntity(bookDto);
        BookEntity storedBook = bookRepository.save(book);
        return tempConverter.entityToDto(storedBook);
    }

    @Override
    public List<BookDto> listAllBooks() {
        List<BookDto> returnValue = new ArrayList<>();
        List<BookEntity> allBooks = bookRepository.findAll();
        for(BookEntity book:allBooks){
            returnValue.add(tempConverter.entityToDto(book));
        }
        return returnValue;
    }

    @Override
    public BookDto getBookById(Integer bookId) {
        Optional<BookEntity> book = bookRepository.findById(bookId);
        BookDto returnValue = null;
        if(book.isPresent()){
            returnValue = tempConverter.entityToDto(book.get());
        }else{
            throw new InstanceUndefinedException(new Error("Book undefined"));
        }
        return returnValue;
    }

    @Override
    public BookDto updateBookById(Integer bookId, BookDto bookDto) {
        BookEntity currentBook = bookRepository.findById(bookId).orElseThrow(()-> new InstanceUndefinedException(new Error("Book undefined")));
        BookEntity bookEntity = tempConverter.dtoToEntity(bookDto);
        bookEntity.setId(currentBook.getId());
        bookEntity.setISBN(currentBook.getISBN());
        BookEntity updateBook = bookRepository.saveAndFlush(bookEntity);
//        bookEntity.setWishlists(currentBook.getWishlists());
//        bookEntity.setReviews(currentBook.getReviews());
//        bookEntity.setAwards(currentBook.getAwards());
        //Treba apdejtovati cartService kada se zavrsi
        return tempConverter.entityToDto(updateBook);
    }

    @Override
    public void deleteBook(Integer bookId) {
        BookDto book = getBookById(bookId);
//cartitemservice treba da se implementira
        bookRepository.deleteById(book.getId());
        //treba da se refreshuje kart nakon ovog
        bookRepository.flush();

    }

    @Override
    public List<BookDto> listAllByGenreId(Integer genreId) {
        List<BookDto> returnValue = new ArrayList<>();
        return null;
    }

    @Override
    public List<BookDto> listAllByAuthorId(Integer authorId) {
        return null;
    }

    @Override
    public List<BookDto> listAllByPublisherId(Integer publisherId) {
        return null;
    }
}
