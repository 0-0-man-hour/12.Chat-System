package com.zeromh.chat.realtime.chatting.receive.application;

import com.zeromh.chat.core.domain.Message;
import org.springframework.kafka.listener.MessageListener;

import java.util.concurrent.ExecutionException;

public interface ReceiveChatPort {
    void subscribe(String userId, String sessionId);

    void unsubscribe(String userId);

    void sendMessage(Message message);
}
