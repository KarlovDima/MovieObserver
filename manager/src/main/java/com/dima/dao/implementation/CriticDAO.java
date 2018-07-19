package com.dima.dao.implementation;

import com.dima.dao.repositories.CriticRepository;
import com.dima.dao.ResourceNotFoundException;
import com.dima.models.Critic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CriticDAO {
    @Autowired
    private CriticRepository criticRepository;

    public List<Critic> getAllCritics() {
        return criticRepository.findAll();
    }

    public Critic createCritic(Critic critic) {
        return criticRepository.save(critic);
    }

    public Critic getCriticById(int id) {
        return criticRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Critic", "id", id));
    }

    public Critic updateCritic(Critic critic) {
        return criticRepository.save(critic);
    }

    public ResponseEntity deleteCritic(int id) {
        Critic critic = criticRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Critic", "id", id));
        criticRepository.delete(critic);

        return ResponseEntity.ok().build();
    }
}