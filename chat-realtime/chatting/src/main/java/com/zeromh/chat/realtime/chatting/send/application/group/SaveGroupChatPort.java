package com.zeromh.chat.realtime.chatting.send.application.group;

import com.zeromh.chat.core.domain.message.GroupMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaveGroupChatPort extends MongoRepository<GroupMessage, String> {
}
