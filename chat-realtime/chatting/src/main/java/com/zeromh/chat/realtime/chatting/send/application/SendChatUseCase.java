package com.zeromh.chat.realtime.chatting.send.application;

import com.zeromh.chat.core.domain.message.GroupMessage;
import com.zeromh.chat.core.domain.message.PersonalMessage;
import com.zeromh.chat.realtime.chatting.send.application.group.GroupServicePort;
import com.zeromh.chat.realtime.chatting.send.application.group.SaveGroupChatPort;
import com.zeromh.chat.realtime.chatting.send.application.personal.SaveChatPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendChatUseCase {

    private final SaveChatPort saveChatPort;
    private final SaveGroupChatPort saveGroupChatPort;
    private final SendChatPort sendChatPort;
    private final CheckConnectPort checkConnectPort;
    private final GroupServicePort groupServicePort;
    private final NotifyMessagePort notifyMessagePort;

    public void saveAndSendMessage(PersonalMessage message) {
        long messageId = IdGenerator.generateNewId(1, 1);
        message.setId(messageId);
        message.setCreateAt(Date.from(Instant.now()));
        saveChatPort.save(message);

        checkConnectPort.checkAndGetConnectionInfo(message.getTo())
                .ifPresentOrElse(
                        server -> sendChatPort.send(server, message),
                        () -> notifyMessagePort.send(message.getTo(), message)
                );
    }

    public void saveAndSendGroupMessage(GroupMessage message) {
        long messageId = IdGenerator.generateNewId(1, 1);
        message.setId(messageId);
        message.setCreateAt(Date.from(Instant.now()));
        saveGroupChatPort.save(message);

        List<String> groupMembers = groupServicePort.getAllGroupMember(message.getChannelId());

        groupMembers.forEach(userId -> checkConnectPort.checkAndGetConnectionInfo(userId)
                .ifPresentOrElse(
                        server -> sendChatPort.send(server, message),
                        () -> notifyMessagePort.send(userId, message))
        );
    }
}
