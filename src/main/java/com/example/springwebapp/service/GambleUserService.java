package com.example.springwebapp.service;

import com.example.springwebapp.model.GambleUser;
import com.example.springwebapp.repository.GambleUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GambleUserService {

    private final GambleUserRepository gambleUserRepository;

    public GambleUserService(GambleUserRepository gambleUserRepository) {
        this.gambleUserRepository = gambleUserRepository;
    }

    public void save(GambleUser user) {
        gambleUserRepository.save(user);
    }

    public GambleUser findByUserNameAndPassword(String userName, String password) {
        return gambleUserRepository.findByUserNameAndPassword(userName, password);
    }

    public void updateMoneyPool(GambleUser user, Integer moneyPool) {
        user.setMoneyPool(user.getMoneyPool() - moneyPool);
        gambleUserRepository.save(user);
    }
}