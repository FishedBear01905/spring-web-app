package com.example.springwebapp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BetHistory {

    public enum BetStatus {
        PENDING, 
        WIN,     
        LOSE,
        REFUND,
        END
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long betRoomId;
    private Long betTargetId;
    private Integer betPrice;
    @Column(nullable = true)
    private Integer payout;
    private LocalDate deadline;
    private Boolean isAfterDeadLine;
    @Enumerated(EnumType.STRING)
    private BetStatus status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBetRoomId() {
        return betRoomId;
    }

    public void setBetRoomId(Long betRoomId) {
        this.betRoomId = betRoomId;
    }

    public Long getBetTargetId() {
        return betTargetId;
    }

    public void setBetTargetId(Long betTargetId) {
        this.betTargetId = betTargetId;
    }

    public Integer getBetPrice() {
        return betPrice;
    }

    public void setBetPrice(Integer betPrice) {
        this.betPrice = betPrice;
    }

    public Integer getPayout() {
        return payout;
    }

    public void setPayout(Integer payout) {
        this.payout = payout;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    
    public Boolean getIsAfterDeadLine() {
        return isAfterDeadLine;
    }

    public void setIsAfterDeadLine(Boolean isAfterDeadLine) {
        this.isAfterDeadLine = isAfterDeadLine;
    }

    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }

}