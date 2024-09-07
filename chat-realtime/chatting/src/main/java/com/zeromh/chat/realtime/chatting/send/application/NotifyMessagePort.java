package com.zeromh.chat.realtime.chatting.send.application;

import com.zeromh.chat.core.domain.message.Message;

public interface NotifyMessagePort {
    void send(String userId, Message message);
}
