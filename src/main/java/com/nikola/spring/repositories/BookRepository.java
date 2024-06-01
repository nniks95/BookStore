package com.nikola.spring.repositories;

import com.nikola.spring.configuration.JpaPersistence;
import com.nikola.spring.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Integer> {
}
