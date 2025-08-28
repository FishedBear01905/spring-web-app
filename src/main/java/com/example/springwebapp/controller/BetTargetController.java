package com.example.springwebapp.controller;

import com.example.springwebapp.model.BetHistory;
import com.example.springwebapp.model.BetTarget;
import com.example.springwebapp.model.GambleRoom;
import com.example.springwebapp.model.GambleUser;
import com.example.springwebapp.service.BetHistoryService;
import com.example.springwebapp.service.BetTargetService;
import com.example.springwebapp.service.GambleRoomService;
import com.example.springwebapp.service.GambleUserService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BetTargetController {

    private final BetTargetService betTargetService;
    private final BetHistoryService betHistoryService;
    private final GambleRoomService gambleRoomService;
    private final GambleUserService gambleUserService;

    public BetTargetController(
        BetTargetService betTargetService,
        BetHistoryService betHistoryService,
        GambleRoomService gambleRoomService,
        GambleUserService gambleUserService
    ) {
        this.betHistoryService = betHistoryService;
        this.betTargetService = betTargetService;
        this.gambleRoomService = gambleRoomService;
        this.gambleUserService = gambleUserService;
    }

    @PostMapping("/bet/save")
    public String saveBetHistory(HttpSession session, @ModelAttribute BetHistory betHistory) {    
        GambleUser user = (GambleUser) session.getAttribute("loggedInUser");
        gambleUserService.updateMoneyPool(user, betHistory.getBetPrice());
        user.setMoneyPool(user.getMoneyPool() - betHistory.getBetPrice());
        
        betHistoryService.save(betHistory);
        return "redirect:/room/list"; 
    }

    @GetMapping("/bet-history")
    public String viewBetHistory(HttpSession session, Model model) {
        GambleUser user = (GambleUser) session.getAttribute("loggedInUser");
        List<BetHistory> betHistorys = betHistoryService.findBetHistoryByUserId(user.getId());
        betHistorys = betHistoryService.updateIsAfterEvent(betHistorys);

        model.addAttribute("betHistorys", betHistorys);
        return "bet/bet-history";
    }

    @PostMapping("/room/{gambleRoomId}/bet-targets")
    public String listBetTargets(@PathVariable Long gambleRoomId, Model model) {
        List<BetTarget> betTargets = betTargetService.findByGambleRoomId(gambleRoomId);
        GambleRoom gambleRoom = gambleRoomService.findRoomById(gambleRoomId);
        model.addAttribute("betTargets", betTargets);
        model.addAttribute("gambleRoom", gambleRoom);
        return "bet/bet-target-list";
    }

    @PostMapping("/room/{roomId}/my-room-bet-targets")
    public String listMyRoomBetTargets(@PathVariable Long roomId, Model model) {
        List<BetTarget> betTargets = betTargetService.findByGambleRoomId(roomId);
        model.addAttribute("betTargets", betTargets);
        model.addAttribute("roomId", roomId);
        return "bet/select-winner";
    }

    @PostMapping("/bet/select-winner/{targetId}")
    public ResponseEntity<String> selectWinner(@PathVariable Long targetId) {
        BetTarget betTarget = betTargetService.findById(targetId);
        Long roomId = betTarget.getGambleRoomId();

        if (betTarget.IsWin()) {
            return ResponseEntity.badRequest().body("This target is already selected as a winner.");
        }

        betTargetService.saveIsWin(targetId,true);
        betHistoryService.selectWinner(roomId, targetId);
        gambleRoomService.afterEvent(roomId);
        return  ResponseEntity.ok("success");
    }
}