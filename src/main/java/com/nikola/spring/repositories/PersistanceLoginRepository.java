package com.nikola.spring.repositories;

import com.nikola.spring.entities.PersistanceLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistanceLoginRepository extends JpaRepository<PersistanceLoginEntity,Integer> {
}
