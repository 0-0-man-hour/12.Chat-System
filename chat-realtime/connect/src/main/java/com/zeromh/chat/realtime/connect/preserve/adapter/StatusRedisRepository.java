package com.zeromh.chat.realtime.connect.preserve.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeromh.chat.core.domain.member.UserStatus;
import com.zeromh.chat.realtime.connect.preserve.application.SaveStatusPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "management.connect-repository", havingValue = "redis")
public class StatusRedisRepository implements SaveStatusPort {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final String STATUS = "status";

    @Override
    public void save(UserStatus userStatus) {
        try {
            redisTemplate.opsForHash().put(STATUS, userStatus.getId(), objectMapper.writeValueAsString(userStatus));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<UserStatus> findById(String id) {
        UserStatus userStatus = null;
        try {
            userStatus = objectMapper.readValue((String) redisTemplate.opsForHash().get(STATUS, id), UserStatus.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(userStatus);

    }

    @Override
    public List<UserStatus> findAllUserStatus() {
        return redisTemplate.opsForHash().entries(STATUS).values().stream()
                .map(str -> {
                    try {
                        return objectMapper.readValue((String) str, UserStatus.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    @Override
    public void remove(UserStatus userStatus) {
        redisTemplate.opsForHash().delete(STATUS, userStatus.getId());
    }
}
