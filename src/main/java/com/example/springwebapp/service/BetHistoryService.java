package com.example.springwebapp.service;

import com.example.springwebapp.model.BetHistory;
import com.example.springwebapp.model.BetHistory.BetStatus;
import com.example.springwebapp.repository.BetHistoryRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BetHistoryService {

    private final BetHistoryRepository betHistoryRepository;

    public BetHistoryService(BetHistoryRepository betHistoryRepository) {
        this.betHistoryRepository = betHistoryRepository;
    }

    public void save(BetHistory betHistory) {
        betHistoryRepository.save(betHistory);
    }
    
    public List<BetHistory> findBetHistoryByUserId(Long userId) {
        return betHistoryRepository.findByUserId(userId);
    }

    public List<BetHistory> updateIsAfterEvent(List<BetHistory> betHistorys) {
        betHistorys.forEach(betHistory -> {
            if (betHistory.getDeadline() != null && betHistory.getDeadline().isBefore(java.time.LocalDate.now())) {
                betHistory.setIsAfterDeadLine(true);
                betHistoryRepository.save(betHistory);
            }
        });

        return betHistorys;
    }

    public void selectWinner(Long roomId,Long targetId) {
        List<BetHistory> historys = betHistoryRepository.findByBetRoomId(roomId);
        historys.forEach(betHistory -> {
            {
                if(betHistory.getStatus() != BetStatus.PENDING) {
                    return; 
                }

                if ( betHistory.getBetTargetId() == targetId) {
                    betHistory.setPayout((int) (betHistory.getBetPrice() * 2.0)); 
                    betHistory.setStatus(BetStatus.WIN);
                }else{                    
                    betHistory.setPayout(0); 
                    betHistory.setStatus(BetStatus.LOSE);
                }
                betHistoryRepository.save(betHistory);
            }
        });
    }

    public void refund(Long roomId){
        List<BetHistory> historys = betHistoryRepository.findByBetRoomId(roomId);
        historys.forEach(betHistory -> {
            if (betHistory.getStatus() == BetStatus.PENDING) {
                betHistory.setPayout(betHistory.getBetPrice()); 
                betHistory.setStatus(BetStatus.REFUND);
                betHistoryRepository.save(betHistory);
            }
        });
    }
}