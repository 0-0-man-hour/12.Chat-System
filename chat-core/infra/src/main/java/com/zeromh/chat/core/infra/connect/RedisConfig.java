package com.zeromh.chat.core.infra.connect;

import com.zeromh.chat.core.infra.ChatConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.zeromh.chat")
public class RedisConfig implements ChatConfig {
}
