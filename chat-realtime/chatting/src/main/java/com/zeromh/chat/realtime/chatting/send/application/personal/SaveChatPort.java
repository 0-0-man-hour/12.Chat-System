package com.zeromh.chat.realtime.chatting.send.application.personal;

import com.zeromh.chat.core.domain.message.PersonalMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaveChatPort extends MongoRepository<PersonalMessage, String> {

}
