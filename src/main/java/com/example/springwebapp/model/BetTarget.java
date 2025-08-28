package com.example.springwebapp.model;

import jakarta.persistence.*;

@Entity
public class BetTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long gambleRoomId;

    private String targetTitle;
    private String targetDescription;

    private boolean isWin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGambleRoomId() {
        return gambleRoomId;
    }

    public void setGambleRoomId(Long gambleRoomId) {
        this.gambleRoomId = gambleRoomId;
    }

    public String getTargetTitle() {
        return targetTitle;
    }

    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }

    public String getTargetDescription() {
        return targetDescription;
    }

    public void setTargetDescription(String targetDescription) {
        this.targetDescription = targetDescription;
    }

    public Boolean IsWin() {
        return isWin;
    }

    public void setWin(Boolean winner) {
        isWin = winner;
    }
}