package com.nikola.spring.repositories;

import com.nikola.spring.entities.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity,Integer> {
}
