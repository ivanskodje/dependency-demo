package com.ivanskodje.dependency.demo.dependencydemo.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemJpaRepository extends JpaRepository<ItemJpaEntity, Long> {
    Optional<ItemJpaEntity> findFirstByName(String name);
}
