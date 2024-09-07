package com.zeromh.chat.realtime.chatting.send.adapter;

import com.zeromh.chat.core.domain.message.Message;
import com.zeromh.chat.realtime.chatting.send.application.NotifyMessagePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotifyMessageSender implements NotifyMessagePort {

    @Override
    public void send(String userId, Message message) {
        log.info("Noti to {}, content: {}", userId, message);
    }
}
