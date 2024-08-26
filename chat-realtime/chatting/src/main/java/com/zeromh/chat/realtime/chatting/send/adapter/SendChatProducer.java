package com.zeromh.chat.realtime.chatting.send.adapter;

import com.zeromh.chat.core.domain.Message;
import com.zeromh.chat.realtime.chatting.send.application.SendChatPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendChatProducer implements SendChatPort {

    private final KafkaTemplate<String, Message> kafkaTemplate;
    @Override
    public void send(String to, Message message) {
        kafkaTemplate.send(to, message);
    }
}
