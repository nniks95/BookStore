package com.nikola.spring.repositories;

import com.nikola.spring.entities.RequestLoyaltyCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLoyaltyCardRepository extends JpaRepository<RequestLoyaltyCardEntity,Integer> {
}
