package com.dima.services.implementation;

import com.dima.dao.DAOFactory;
import com.dima.models.Critic;
import com.dima.services.CriticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriticServiceImpl implements CriticService {
    @Autowired
    private DAOFactory daoFactory;

    @Override
    public Critic createCritic(Critic critic) {
        return daoFactory.getCriticDAO().createCritic(critic);
    }

    @Override
    public List<Critic> getAllCritics() {
        return daoFactory.getCriticDAO().getAllCritics();
    }

    @Override
    public Critic getCriticById(int id) {
        return daoFactory.getCriticDAO().getCriticById(id);
    }

    @Override
    public Critic updateCritic(int id, Critic critic) {
        return daoFactory.getCriticDAO().updateCritic(id, critic);
    }

    @Override
    public ResponseEntity<Critic> deleteCritic(int id) {
        return daoFactory.getCriticDAO().deleteCritic(id);
    }
}
