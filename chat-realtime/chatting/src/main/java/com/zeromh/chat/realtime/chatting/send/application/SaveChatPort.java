package com.zeromh.chat.realtime.chatting.send.application;

import com.zeromh.chat.core.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaveChatPort extends MongoRepository<Message, String> {

}
