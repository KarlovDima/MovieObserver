package com.dima.dao.repositories;

import com.dima.models.Critic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriticRepository extends JpaRepository<Critic, Integer> {
}
