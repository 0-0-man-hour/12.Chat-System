package com.zeromh.chat.realtime.chatting.send.adapter;

import com.zeromh.chat.core.domain.Message;
import com.zeromh.chat.realtime.chatting.send.application.SendChatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final SendChatUseCase sendChatUseCase;

    @GetMapping(path = "/test")
    public void messageTest() {
        sendChatUseCase.saveAndSend(new Message(""));
    }
}
