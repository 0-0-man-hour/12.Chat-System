package com.zeromh.chat.realtime.chatting.send.application;

import com.zeromh.chat.core.domain.message.Message;
import com.zeromh.chat.core.domain.message.PersonalMessage;

public interface SendChatPort {
    void send(String to, Message message);
}
