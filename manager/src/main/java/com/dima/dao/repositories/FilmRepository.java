package com.dima.dao.repositories;

import com.dima.models.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
    @Query("select f from Film f where f.isActive = true")
    List<Film> findAllActiveFilms();
}
