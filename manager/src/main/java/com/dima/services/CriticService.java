package com.dima.services;

import com.dima.models.entity.Critic;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CriticService {
    Critic createCritic(Critic critic);

    List<Critic> getAllCritics();

    Critic getCriticById(int id);

    Critic updateCritic(int id, Critic critic);

    ResponseEntity<Critic> deleteCritic(int id);
}
