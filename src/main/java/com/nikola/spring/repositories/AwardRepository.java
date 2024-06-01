package com.nikola.spring.repositories;

import com.nikola.spring.entities.AwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends JpaRepository<AwardEntity,Integer> {
}
