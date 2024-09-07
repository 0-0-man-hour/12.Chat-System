package com.zeromh.chat.realtime.connect.preserve.adapter;

import com.zeromh.chat.core.domain.member.UserStatus;
import com.zeromh.chat.realtime.connect.preserve.application.PreserveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class PreserveController {

    private final PreserveUseCase preserveUseCase;

    @MessageMapping("/heartbeat")
    public void sendHeartbeat(UserStatus userStatus) {
        userStatus.setLastUpdateAt(LocalDateTime.now());
        preserveUseCase.changeUserStatus(userStatus);
    }
}
