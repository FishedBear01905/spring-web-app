package com.example.springwebapp.service;

import org.springframework.stereotype.Service;

import com.example.springwebapp.model.BetHistory;
import com.example.springwebapp.model.BetHistory.BetStatus;
import com.example.springwebapp.model.GambleUser;
import com.example.springwebapp.repository.BetHistoryRepository;
import com.example.springwebapp.repository.GambleUserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class PayoutService {

    private BetHistoryRepository betHistoryRepository;
    private GambleUserRepository gambleUserRepository;

    public PayoutService(
        BetHistoryRepository betHistoryRepository,
        GambleUserRepository gambleUserRepository
    ) {
        this.betHistoryRepository = betHistoryRepository;
        this.gambleUserRepository = gambleUserRepository;
    }

     public void cancelBet(HttpSession session,Long id) {
        BetHistory history = betHistoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("BetHistory not found with id: " + id));
        history.setPayout(history.getBetPrice()); 
        history.setStatus(BetStatus.END);
        GambleUser user = (GambleUser) session.getAttribute("loggedInUser");
        updateUserMoneyPool(user,history.getPayout());
        betHistoryRepository.save(history);
    }

    public void payoutBet(HttpSession session,Long id){
        BetHistory history = betHistoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("BetHistory not found with id: " + id));
        history.setStatus(BetStatus.END);
        GambleUser user = (GambleUser) session.getAttribute("loggedInUser");
        updateUserMoneyPool(user,history.getPayout());
        betHistoryRepository.save(history);
    }

    private void updateUserMoneyPool(GambleUser user,Integer payout) {
        Integer moneyPool = user.getMoneyPool();
        user.setMoneyPool(moneyPool + payout);
        gambleUserRepository.save(user);
    }
}