package com.example.springwebapp.repository;

import com.example.springwebapp.model.GambleRoom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GambleRoomRepository extends JpaRepository<GambleRoom, Long> {
    List<GambleRoom> findRoomsByUserId(Long id);
    GambleRoom findRoomById(Long id);
}