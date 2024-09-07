package com.zeromh.chat.realtime.connect.preserve.adapter;

import com.zeromh.chat.core.domain.member.UserStatus;
import com.zeromh.chat.realtime.connect.preserve.application.SaveStatusPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "management.connect-repository", havingValue = "mongo")
public class StatusMongoRepository implements SaveStatusPort {

    private final String COLLECTION_NAME = "userStatus";
    private final MongoTemplate mongoTemplate;
    @Override
    public Optional<UserStatus> findById(String userId) {
        Query query = new Query(Criteria.where("_id").is(userId));
        UserStatus userStatus = mongoTemplate.findOne(query, UserStatus.class, COLLECTION_NAME);

        return Optional.ofNullable(userStatus);
    }

    @Override
    public void save(UserStatus userStatus) {
        mongoTemplate.save(userStatus, COLLECTION_NAME);
    }

}
