package com.zeromh.chat.realtime.chatting.send.adapter;

import com.zeromh.chat.core.domain.Message;
import com.zeromh.chat.realtime.chatting.send.application.SendChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChattingController {

    private final SendChatUseCase sendChatUseCase;

    @MessageMapping("/greeting")
    public String handle(String greeting) {
        System.out.println("greet");
        return "[]: " + greeting;
    }

    @MessageMapping("/send")
    public String sendMessage(Message message) {
        sendChatUseCase.saveAndSend(message);
        return "";
    }

}
