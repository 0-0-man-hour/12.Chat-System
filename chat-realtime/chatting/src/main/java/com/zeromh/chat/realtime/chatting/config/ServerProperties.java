package com.zeromh.chat.realtime.chatting.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "server")
public class ServerProperties {
    String address;
    Integer port;

    public String getServerInfo() {
        return address+"-"+port;
    }
}
