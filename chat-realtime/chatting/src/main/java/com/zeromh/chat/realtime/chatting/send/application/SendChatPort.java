package com.zeromh.chat.realtime.chatting.send.application;

import com.zeromh.chat.core.domain.Message;

public interface SendChatPort {
    void send(String to, Message message);
}
