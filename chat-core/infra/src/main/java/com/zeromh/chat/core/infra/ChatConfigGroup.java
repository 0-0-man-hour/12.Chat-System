package com.zeromh.chat.core.infra;

import com.zeromh.chat.core.infra.connect.RedisConfig;
import com.zeromh.chat.core.infra.data.MongoConfig;
import com.zeromh.chat.core.infra.messaging.KafkaConfig;
import com.zeromh.chat.core.infra.messaging.WebSocketConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatConfigGroup {

	MONGO(MongoConfig.class),
	KAFKA(KafkaConfig.class),
	WEBSOCKET(WebSocketConfig.class),
	REDIS(RedisConfig.class)
	;

	private final Class<? extends ChatConfig> configClass;

}
