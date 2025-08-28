package com.example.springwebapp.repository;

import com.example.springwebapp.model.BetTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BetTargetRepository extends JpaRepository<BetTarget, Long> {
    List<BetTarget> findByGambleRoomId(Long roomID);
}