package com.zeromh.chat.core.infra.data;

import com.zeromh.chat.core.infra.ChatConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.zeromh.chat")
public class MongoConfig implements ChatConfig {
}
