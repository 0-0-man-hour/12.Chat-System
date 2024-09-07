package com.zeromh.chat.realtime.chatting.receive.application;

import com.zeromh.chat.core.domain.member.ConnectStatus;
import com.zeromh.chat.core.domain.member.UserStatus;
import com.zeromh.chat.core.domain.message.PersonalMessage;
import com.zeromh.chat.realtime.chatting.config.ServerProperties;
import com.zeromh.chat.realtime.chatting.send.application.CheckConnectPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReceiveChatUseCase {

    private final ReceiveChatPort receiveChatPort;


    public void subscribe(String userId, String sessionId) {
        receiveChatPort.subscribe(userId, sessionId);
    }

    public void unsubscribe(String userId) {
        receiveChatPort.unsubscribe(userId);
    }

    public void receiveMessage(PersonalMessage personalMessage) {
        receiveChatPort.sendMessage(personalMessage);
    }
}
