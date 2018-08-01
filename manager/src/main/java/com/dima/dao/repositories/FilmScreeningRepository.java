package com.dima.dao.repositories;

import com.dima.models.entity.FilmScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmScreeningRepository extends JpaRepository<FilmScreening, Integer> {
}
