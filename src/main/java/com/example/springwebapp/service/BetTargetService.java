package com.example.springwebapp.service;

import com.example.springwebapp.model.BetTarget;
import com.example.springwebapp.repository.BetTargetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BetTargetService {

    private final BetTargetRepository betTargetRepository;

    public BetTargetService(BetTargetRepository betTargetRepository) {
        this.betTargetRepository = betTargetRepository;
    }

    public void save(BetTarget betTarget) {
        betTargetRepository.save(betTarget);
    }

    public void saveTargetList(List<BetTarget> betTargets, Long roomId, LocalDate deadline) {
        betTargets.forEach(target -> {
            target.setGambleRoomId(roomId); 
            betTargetRepository.save(target);
        });
    }

    public BetTarget findById(Long id) {
        return betTargetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("BetTarget not found with id: " + id));
    }

    public List<BetTarget> findByGambleRoomId(Long gambleRoomId ) {
        return betTargetRepository.findByGambleRoomId(gambleRoomId );
    }

    public boolean hasWinner(Long gambleRoomId) {
        List<BetTarget> betTargets = betTargetRepository.findByGambleRoomId(gambleRoomId);
        for (BetTarget betTarget : betTargets) {
            if(betTarget.IsWin()){
                return true;
            }
        }
        return false;
    }

    public BetTarget saveIsWin(Long id, boolean isWin) {
        BetTarget betTarget = betTargetRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("BetTarget not found with id: " + id));
        betTarget.setWin(isWin);
        return betTargetRepository.save(betTarget);
    }
}