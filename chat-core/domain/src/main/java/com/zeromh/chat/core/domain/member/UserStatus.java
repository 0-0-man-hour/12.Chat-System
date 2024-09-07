package com.zeromh.chat.core.domain.member;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatus {
    String id;
    ConnectStatus status;
    String server;
    LocalDateTime lastUpdateAt;
}
