package com.zeromh.chat.realtime.connect.preserve.application;

import com.zeromh.chat.core.domain.member.UserStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SaveStatusPort {
    void save(UserStatus userStatus);

    Optional<UserStatus> findById(String id);

    List<UserStatus> findAllUserStatus();

    void remove(UserStatus userStatus);
}
