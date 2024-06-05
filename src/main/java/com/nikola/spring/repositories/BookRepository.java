package com.nikola.spring.repositories;

import com.nikola.spring.configuration.JpaPersistence;
import com.nikola.spring.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Integer> {

    List<BookEntity> findAllByAuthorId(Integer authorId);
    List<BookEntity> findAllByGenreId(Integer GenreId);
    List<BookEntity> findAllByPublisherId(Integer PublisherId);
}
