package com.zeromh.chat.core.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ChannelMembers {
    long id;
    List<String> members;
}
