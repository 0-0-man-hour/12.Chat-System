package com.zeromh.chat.core.infra;

import com.zeromh.chat.core.infra.data.MongoConfig;
import com.zeromh.chat.core.infra.messaging.KafkaConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatConfigGroup {

	MONGO(MongoConfig.class),
	KAFKA(KafkaConfig.class)
	;

	private final Class<? extends ChatConfig> configClass;

}
