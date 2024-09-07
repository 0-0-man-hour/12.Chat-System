package com.zeromh.chat.core.domain.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupMessage implements Message{
    long id;
    long channelId;
    String userId;
    String content;
    Date createAt;

}
