package com.nikola.spring.repositories;

import com.nikola.spring.entities.LoyaltyCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCardEntity,Integer> {
}
