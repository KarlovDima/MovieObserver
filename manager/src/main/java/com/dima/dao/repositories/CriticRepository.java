package com.dima.dao.repositories;

import com.dima.models.entity.Critic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriticRepository extends JpaRepository<Critic, Integer> {
    @Query("select c from Critic c left join Review r on c = r.critic and r.film.id = :filmId where r.id is null")
    List<Critic> getCriticsWithoutReview(@Param("filmId") int filmId);
}
