package com.zeromh.chat.realtime.chatting.receive.application;

import com.zeromh.chat.core.domain.message.PersonalMessage;

public interface ReceiveChatPort {
    void subscribe(String userId, String sessionId);

    void unsubscribe(String userId);

    void sendMessage(PersonalMessage personalMessage);
}
