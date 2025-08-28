package com.example.springwebapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class GambleRoom {

    public enum RoomStatus {
        PENDING, 
        REFUND,
        END
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String roomTitle;
    private String roomDescription;
    private Integer betMoneyMin;
    private Integer betMoneyMax;
    private Double commission; 
    private LocalDate date; 
    private LocalDate deadline; 
    @Transient
    private List<BetTarget> betTargets;
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
    private Boolean isAfterDeadLine;

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

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Integer getBetMoneyMin() {
        return betMoneyMin;
    }

    public void setBetMoneyMin(Integer betMoneyMin) {
        this.betMoneyMin = betMoneyMin;
    }

    public Integer getBetMoneyMax() {
        return betMoneyMax;
    }

    public void setBetMoneyMax(Integer betMoneyMax) {
        this.betMoneyMax = betMoneyMax;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public List<BetTarget> getBetTargets() {
        return betTargets;
    }

    public void setBetTargets(List<BetTarget> betTargets) {
        this.betTargets = betTargets;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Boolean getIsAfterDeadLine() {
        return isAfterDeadLine;
    }

    public void setIsAfterDeadLine(Boolean isAfterDeadLine) {
        this.isAfterDeadLine = isAfterDeadLine;
    }

}