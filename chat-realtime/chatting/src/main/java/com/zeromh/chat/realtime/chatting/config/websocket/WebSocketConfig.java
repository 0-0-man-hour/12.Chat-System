package com.zeromh.chat.realtime.chatting.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트로 메시지를 전달할 때 사용할 경로 설정 (예: /topic, /queue)
        // config.enableSimpleBroker("/topic");
        // 클라이언트에서 메시지를 보낼 때 사용할 경로 설정 (예: /app)
        registry.setApplicationDestinationPrefixes("/pub");
        registry.setUserDestinationPrefix("/user"); // 사용자 목적지 프리픽스 설정
        registry.enableSimpleBroker("/queue","/topic");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 연결 엔드포인트 설정
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/ws").setAllowedOrigins("*");
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        // 여기서 유니크한 사용자 ID를 생성하거나 할당
                        // 예시: 유니크한 UUID를 사용자 ID로 사용
                        return UUID.randomUUID()::toString;
                    }
                });

        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        // 여기서 유니크한 사용자 ID를 생성하거나 할당
                        // 예시: 유니크한 UUID를 사용자 ID로 사용
                        return UUID.randomUUID()::toString;
                    }
                })
                .withSockJS();

    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
////        registration.interceptors(new ChannelInterceptor() {
//            // 메시지가 실제로 채널로 전송되기 전에 호출됨
////            @Override
////            public Message<?> preSend(Message<?> message, MessageChannel channel) {
////                StompHeaderAccessor headerAccessor =
////                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
////
////                // 헤더에서 유저명을 가져온다.
////                List<String> usernames = Optional
////                        .ofNullable(headerAccessor.getNativeHeader("username"))
////                        .orElseGet(Collections::emptyList);
////
////                // 처음 접속 시도시 유저 데이터를 심어준다.
////                if (StompCommand.CONNECT.equals(headerAccessor.getCommand()) && !usernames.isEmpty()) {
////                    headerAccessor.setUser(new SimpleUsernamePrincipal(usernames.get(0)));
////                    sessionKeys.put(usernames.get(0), headerAccessor.getSessionId());
////                }
////
////                // 사용자 접속 해제시 사용자 큐를 삭제한다.
////                if (StompCommand.DISCONNECT.equals(headerAccessor.getCommand())) {
////                    String sessionKey = sessionKeys.get(headerAccessor.getUser().getName());
////                    // message-user는 생성되는 큐의 접두사
////                    // /users/queue/message의 message를 가져와 -user를 붙여만든다.
////                    new RabbitAdmin(rabbitTemplate).deleteQueue("message-user" + sessionKey);
////                    sessionKeys.remove(sessionKey);
////                }
////
////                return message;
////            }
////        });
//    }
}
