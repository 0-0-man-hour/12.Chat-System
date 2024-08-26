package com.zeromh.chat.realtime.chatting.receive.adapter.message;

import com.zeromh.chat.core.domain.Message;
import com.zeromh.chat.realtime.chatting.receive.application.ReceiveChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiveChatConsumer {

    private final ReceiveChatUseCase  receiveChatUseCase;

    @KafkaListener(topics = "${server.name}")
    public void consume(Message message) {
        System.out.println(message);
        receiveChatUseCase.receiveMessage(message);
    }
}
