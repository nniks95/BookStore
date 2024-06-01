package com.nikola.spring.repositories;

import com.nikola.spring.entities.BookImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepository extends JpaRepository<BookImageEntity,Integer> {
}
