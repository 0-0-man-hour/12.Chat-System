package com.zeromh.chat.realtime.chatting.send.adapter;

import com.zeromh.chat.core.domain.member.ConnectStatus;
import com.zeromh.chat.core.domain.member.UserStatus;
import com.zeromh.chat.realtime.chatting.send.application.CheckConnectPort;
import com.zeromh.chat.realtime.connect.preserve.application.PreserveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckConnectAdapter implements CheckConnectPort {

    private final PreserveUseCase preserveUseCase;

    @Override
    public Optional<String> checkAndGetConnectionInfo(String userId) {
        return preserveUseCase.getUserStatus(userId)
                .filter(status -> status.getStatus().equals(ConnectStatus.ONLINE))
                .map(UserStatus::getServer);

    }

    @Override
    public void updateUserInfo(UserStatus userStatus) {
        preserveUseCase.changeUserStatus(userStatus);
    }
}
