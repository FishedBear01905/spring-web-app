package com.example.springwebapp.service;

import com.example.springwebapp.model.GambleRoom;
import com.example.springwebapp.model.GambleRoom.RoomStatus;
import com.example.springwebapp.repository.GambleRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GambleRoomService {

    private final GambleRoomRepository gambleRoomRepository;

    public GambleRoomService(GambleRoomRepository gambleRoomRepository) {
        this.gambleRoomRepository = gambleRoomRepository;
    }

    public GambleRoom findRoomById(Long gambleRoomId) {
        return gambleRoomRepository.findRoomById(gambleRoomId);
    }

    public void save(GambleRoom room) {
        gambleRoomRepository.save(room);
    }

    public String validateRoom(GambleRoom room) {
        // 必須項目がnullの場合のチェック
        if (room.getRoomTitle() == null || room.getRoomTitle().trim().isEmpty()) {
            return "部屋のタイトルを入力してください。";
        }
        if (room.getRoomDescription() == null || room.getRoomDescription().trim().isEmpty()) {
            return "部屋の詳細を入力してください。";
        }
        if (room.getDate() == null) {
            return "開催日を入力してください。";
        }
        if (room.getBetMoneyMin() == null) {
            return "最小掛け金を入力してください。";
        }
        if (room.getBetMoneyMax() == null) {
            return "最大掛け金を入力してください。";
        }
        if (room.getCommission() == null) {
            return "手数料を入力してください。";
        }
    
        // 開催日も締め切り日も現在時刻より後にできない
        if (room.getDate() != null && room.getDate().isBefore(java.time.LocalDate.now())) {
            return "開催日は現在時刻より後に設定してください。";
        }
        if (room.getDeadline() != null && room.getDeadline().isBefore(java.time.LocalDate.now())) {
            return "締め切り日は現在時刻より後に設定してください。";
        }
    
        // 締め切り日が未記入の時開催日と同日になる
        if (room.getDeadline() == null) {
            room.setDeadline(room.getDate());
        }
    
        // 開催日が締め切り日より前だとエラーになる
        if (room.getDate() != null && room.getDeadline() != null && room.getDate().isBefore(room.getDeadline())) {
            return "開催日は締め切り日より後に設定してください。";
        }
    
        // 手数料は10％より上にはできない
        if (room.getCommission() != null && room.getCommission() > 10.0) {
            return "手数料は10％以下に設定してください。";
        }
    
        // 最小掛け金は最大掛け金より大きくできない
        if (room.getBetMoneyMin() != null && room.getBetMoneyMax() != null && room.getBetMoneyMin() > room.getBetMoneyMax()) {
            return "最小掛け金は最大掛け金以下に設定してください。";
        }
    
        // 最小掛け金は0にできない
        if (room.getBetMoneyMin() != null && room.getBetMoneyMin() <= 0) {
            return "最小掛け金は0より大きい値に設定してください。";
        }

        // betTargetsが2つ以上選択されていなければエラー
        if (room.getBetTargets() == null || room.getBetTargets().size() < 2) {
            return "Bet Targetsは2つ以上設定してください。";
        }
    
        return null;
    }

    public List<GambleRoom> getAllRooms() {
        return gambleRoomRepository.findAll(); 
    }

    public List<GambleRoom> findRoomsByUserId(Long id) {
        return gambleRoomRepository.findRoomsByUserId(id);
    }

    public void afterEvent(Long roomId) {
        GambleRoom room = gambleRoomRepository.findRoomById(roomId);
        room.setStatus(RoomStatus.END);
        gambleRoomRepository.save(room);
    }

    public void refund(Long roomId) {
        GambleRoom room = gambleRoomRepository.findRoomById(roomId);
        room.setStatus(RoomStatus.REFUND);
        gambleRoomRepository.save(room);
    }

    public List<GambleRoom> updateIsAfterDeadLine(List<GambleRoom> rooms) {
        rooms.forEach(room -> {
            if (room.getDeadline() != null && room.getDeadline().isBefore(java.time.LocalDate.now())) {
                room.setIsAfterDeadLine(true);
                gambleRoomRepository.save(room);
            }
        });

        return rooms;
    }
}