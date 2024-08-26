package com.zeromh.chat.core.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Message {
    String from;
    String to;
    String content;

    public Message(String content) {
        this.content = "test";
    }
//    public static Message toMessage() {
//
//    }
}
