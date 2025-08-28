package com.example.springwebapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springwebapp.service.PayoutService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PayoutController {

    private final PayoutService payoutService;

    public PayoutController(
        PayoutService payoutService
    ) {
        this.payoutService = payoutService;
    }

    @PostMapping("/bet/cancel/{historyId}")
    public ResponseEntity<String> cancelBet(HttpSession session,@PathVariable Long historyId) {
        payoutService.cancelBet(session,historyId);
        return ResponseEntity.ok("Bet cancelled successfully");
    }

    @PostMapping("/bet/payout/{historyId}")
    public ResponseEntity<String> payoutBet(HttpSession session,@PathVariable Long historyId) {
        payoutService.payoutBet(session,historyId);
        return ResponseEntity.ok("Bet cancelled successfully");
    }
}
