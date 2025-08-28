package com.example.springwebapp.controller;

import com.example.springwebapp.service.BetHistoryService;
import com.example.springwebapp.service.BetTargetService;
import com.example.springwebapp.model.GambleRoom;
import com.example.springwebapp.model.GambleUser;
import com.example.springwebapp.service.GambleRoomService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RoomController {

    private final GambleRoomService gambleRoomService;
    private final BetTargetService betTargetService;
    private final BetHistoryService betHistoryService;

    public RoomController(
        GambleRoomService gambleRoomService,
        BetTargetService betTargetService,
        BetHistoryService betHistoryService
    ) {
        this.gambleRoomService = gambleRoomService;
        this.betTargetService = betTargetService;
        this.betHistoryService = betHistoryService;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "home"; 
    }

    @GetMapping("/room/form")
    public String showRoomForm(Model model) {
        model.addAttribute("room", new GambleRoom());
        model.addAttribute("errorMessage", null);
        return "room/room-form";
    }

    @GetMapping("/room/list")
    public String listAllRooms(Model model) {
        List<GambleRoom> rooms = gambleRoomService.getAllRooms();
        rooms = gambleRoomService.updateIsAfterDeadLine(rooms);
        model.addAttribute("rooms", rooms);
        return "room/room-list"; 
    }

    @PostMapping("/room/save")
    public String saveRoom(@ModelAttribute GambleRoom room,Model model) {
        String errorStatus = gambleRoomService.validateRoom(room);
        if(errorStatus != null) {
            model.addAttribute("room", room);
            model.addAttribute("errorMessage", errorStatus);
            return "room/room-form";
        }
        gambleRoomService.save(room);
        betTargetService.saveTargetList(room.getBetTargets(), room.getId(), room.getDeadline());    
        return "redirect:/room/list"; 
    }
    

    @GetMapping("/my-rooms")
    public String viewMyRooms(HttpSession session, Model model) {
        GambleUser user = (GambleUser) session.getAttribute("loggedInUser");
        List<GambleRoom> myRooms = gambleRoomService.findRoomsByUserId(user.getId());
        model.addAttribute("myRooms", myRooms);
        return "room/my-rooms";
    }

    @PostMapping("/room/refund/{roomId}")
    public String refundRoom(@PathVariable Long roomId) {
        gambleRoomService.refund(roomId);
        betHistoryService.refund(roomId);
        return "redirect:/room/list"; 
    }
}