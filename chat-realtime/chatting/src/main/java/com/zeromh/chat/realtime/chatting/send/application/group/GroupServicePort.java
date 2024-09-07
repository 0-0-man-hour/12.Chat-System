package com.zeromh.chat.realtime.chatting.send.application.group;

import java.util.List;

public interface GroupServicePort {
    List<String> getAllGroupMember(long channelId);
}
