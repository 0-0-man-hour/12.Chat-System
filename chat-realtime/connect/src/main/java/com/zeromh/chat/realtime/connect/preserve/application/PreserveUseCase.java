package com.zeromh.chat.realtime.connect.preserve.application;

import com.zeromh.chat.core.domain.member.ConnectStatus;
import com.zeromh.chat.core.domain.member.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PreserveUseCase {

    private final SaveStatusPort saveStatusPort;
//    private final NotifyStatusPort notifyStatusPort;

    public void changeUserStatus(UserStatus userStatus) {
        saveStatusPort.save(userStatus);
        if (userStatus.getStatus().equals(ConnectStatus.OFFLINE)) {
            //send change status
        }
    }

    public Optional<UserStatus> getUserStatus(String userId) {
        return saveStatusPort.findById(userId);
    }

    public void checkUserStatusIsOld() {
        saveStatusPort.findAllUserStatus()
                .stream()
                .filter(userStatus -> userStatus.isOld(30L))
                .forEach(userStatus -> {
                    //send change status
                    saveStatusPort.remove(userStatus);
                });
    }

}
