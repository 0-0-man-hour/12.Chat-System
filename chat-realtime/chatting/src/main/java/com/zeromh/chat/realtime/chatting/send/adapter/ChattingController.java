package com.zeromh.chat.realtime.chatting.send.adapter;

import com.zeromh.chat.core.domain.message.GroupMessage;
import com.zeromh.chat.core.domain.message.PersonalMessage;
import com.zeromh.chat.realtime.chatting.send.application.SendChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChattingController {

    private final SendChatUseCase sendChatUseCase;

    @MessageMapping("/send")
    public void sendMessage(PersonalMessage personalMessage) {
        sendChatUseCase.saveAndSendMessage(personalMessage);
    }

    @MessageMapping("/group/send")
    public void sendGroupMessage(GroupMessage message) {
        sendChatUseCase.saveAndSendGroupMessage(message);
    }

}
