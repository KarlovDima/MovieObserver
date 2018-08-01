package com.dima.dao.implementation;

import com.dima.dao.ResourceNotFoundException;
import com.dima.dao.repositories.CriticRepository;
import com.dima.models.entity.Critic;
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

    public Critic updateCritic(int id, Critic updatedCritic) {
        Critic critic = criticRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Critic", "id", id));

        critic.setName(updatedCritic.getName());
        critic.setWorkBeginning(updatedCritic.getWorkBeginning());
        critic.setWorkEnding(updatedCritic.getWorkEnding());
        critic.setReviews(updatedCritic.getReviews());
        critic.setPort(updatedCritic.getPort());
        critic.setHost(updatedCritic.getHost());

        return criticRepository.save(critic);
    }

    public ResponseEntity deleteCritic(int id) {
        Critic critic = criticRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Critic", "id", id));
        criticRepository.delete(critic);

        return ResponseEntity.ok().build();
    }

    public List<Critic> getCriticsWithoutReview(int filmId){
        return criticRepository.getCriticsWithoutReview(filmId);
    }
}