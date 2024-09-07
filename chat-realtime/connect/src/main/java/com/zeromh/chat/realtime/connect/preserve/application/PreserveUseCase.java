package com.zeromh.chat.realtime.connect.preserve.application;

import com.zeromh.chat.core.domain.member.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PreserveUseCase {

    private final SaveStatusPort saveStatusPort;

    public void changeUserStatus(UserStatus userStatus) {
//        Optional<UserStatus> saveUserStatus = saveStatusPort.findById(userStatus.getId());
        saveStatusPort.save(userStatus);
    }

    public Optional<UserStatus> getUserStatus(String userId) {
        return saveStatusPort.findById(userId);
    }
}
