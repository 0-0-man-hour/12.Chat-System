package com.zeromh.chat.realtime.chatting.config;

import com.zeromh.chat.core.infra.ChatConfigGroup;
import com.zeromh.chat.core.infra.EnableChatConfig;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableChatConfig({
	ChatConfigGroup.MONGO,
	ChatConfigGroup.KAFKA,
	ChatConfigGroup.WEBSOCKET,
	ChatConfigGroup.REDIS,
})
class InfraConfig {
}
