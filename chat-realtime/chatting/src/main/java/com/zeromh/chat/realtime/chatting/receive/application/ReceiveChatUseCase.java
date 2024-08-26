package com.zeromh.chat.realtime.chatting.receive.application;

import com.zeromh.chat.core.domain.Message;
import com.zeromh.chat.realtime.chatting.temp.MyHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiveChatUseCase {

    private final ReceiveChatPort receiveChatPort;
//    private final MyHandler myHandler;


    public void subscribe(String userId, String sessionId) {
        receiveChatPort.subscribe(userId, sessionId);
    }

    public void unsubscribe(String id) {
        receiveChatPort.unsubscribe(id);
    }

    public void receiveMessage(Message message) {
        receiveChatPort.sendMessage(message);
//        myHandler.sendMessage(message);
    }
}
