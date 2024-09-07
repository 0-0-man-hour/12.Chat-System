package com.zeromh.chat.realtime.chatting.receive.adapter.message;

import com.zeromh.chat.core.domain.message.Message;
import com.zeromh.chat.core.domain.message.PersonalMessage;
import com.zeromh.chat.realtime.chatting.receive.application.ReceiveChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiveChatConsumer {

    private final ReceiveChatUseCase receiveChatUseCase;

    @KafkaListener(topics = "${server.address}-${server.port}")
    public void consume(Message personalMessage) {
        System.out.println(personalMessage);
        receiveChatUseCase.receiveMessage((PersonalMessage) personalMessage);
    }
}
