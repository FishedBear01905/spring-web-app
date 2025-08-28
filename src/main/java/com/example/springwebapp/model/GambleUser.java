package com.example.springwebapp.model;

import jakarta.persistence.*;

@Entity
public class GambleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String citizenId;
    private Integer moneyPool;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public Integer getMoneyPool() {
        return moneyPool;
    }

    public void setMoneyPool(Integer moneyPool) {
        this.moneyPool = moneyPool;
    }
}