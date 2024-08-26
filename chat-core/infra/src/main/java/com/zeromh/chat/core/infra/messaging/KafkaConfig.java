package com.zeromh.chat.core.infra.messaging;

import com.zeromh.chat.core.infra.ChatConfig;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@EntityScan("com.zeromh.chat")
public class KafkaConfig implements ChatConfig {
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Bean
//    public AdminClient adminClient() {
//        Properties properties = new Properties();
//        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        return AdminClient.create(properties);
//    }
}
