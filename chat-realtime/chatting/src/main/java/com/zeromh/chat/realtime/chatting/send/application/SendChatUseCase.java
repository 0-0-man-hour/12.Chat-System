package com.zeromh.chat.realtime.chatting.send.application;

import com.zeromh.chat.core.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendChatUseCase {

    private final SaveChatPort saveChatPort;
    private final SendChatPort sendChatPort;

    public void saveAndSend(Message message) {

//        saveChatPort.save(message);
        //1 데이터 저장
        //2 유저 접속 정보 가져옴
        //3
        sendChatPort.send("server1", message);
    }
}
