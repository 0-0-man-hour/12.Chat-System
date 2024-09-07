package com.zeromh.chat.core.domain.message;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonalMessage implements Message{
    long id;
    String from;
    String to;
    String content;
    Date createAt;
}
