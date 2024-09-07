package com.zeromh.chat.realtime.chatting.receive.adapter.message;

import com.zeromh.chat.core.domain.message.PersonalMessage;
import com.zeromh.chat.realtime.chatting.receive.application.ReceiveChatPort;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReceiveChatSender implements ReceiveChatPort {

    private final SimpMessagingTemplate messagingTemplate;


    private final Map<String, String> sessions = new HashMap<>();

    @Override
    public void subscribe(String userId, String sessionId) {
        System.out.println(userId + " " + sessionId);

        sessions.put(userId, sessionId);
    }

    @Override
    public void unsubscribe(String userId) {
        sessions.remove(userId);
    }

    @Override
    public void sendMessage(PersonalMessage personalMessage) {
        messagingTemplate.convertAndSendToUser(sessions.get(personalMessage.getTo()), "/queue/chat", personalMessage);
    }

}
