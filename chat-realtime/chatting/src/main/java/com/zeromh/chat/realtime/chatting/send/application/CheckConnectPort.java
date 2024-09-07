package com.zeromh.chat.realtime.chatting.send.application;

import com.zeromh.chat.core.domain.member.UserStatus;

import java.util.List;
import java.util.Optional;

public interface CheckConnectPort {

    Optional<String> checkAndGetConnectionInfo(String userId);

    void updateUserInfo(UserStatus userStatus);
}
