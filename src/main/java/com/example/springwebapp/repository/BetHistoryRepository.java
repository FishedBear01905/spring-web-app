package com.example.springwebapp.repository;

import com.example.springwebapp.model.BetHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BetHistoryRepository extends JpaRepository<BetHistory, Long> {
    List<BetHistory> findByUserId(Long userId);
    List<BetHistory> findByBetRoomId(Long roomId);
}