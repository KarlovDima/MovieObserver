package com.dima.services.implementation;

import com.dima.dao.DAOFactory;
import com.dima.models.Critic;
import com.dima.services.CriticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriticServiceImpl implements CriticService {
    @Autowired
    private DAOFactory daoFactory;

    @Override
    public Critic createCritic(Critic critic) {
        return daoFactory.getCriticDAO().create(critic);
    }
}
