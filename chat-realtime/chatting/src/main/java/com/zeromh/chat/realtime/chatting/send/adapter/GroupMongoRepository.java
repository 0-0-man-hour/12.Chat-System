package com.zeromh.chat.realtime.chatting.send.adapter;

import com.zeromh.chat.core.domain.member.ChannelMembers;
import com.zeromh.chat.realtime.chatting.send.application.group.GroupServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupMongoRepository implements GroupServicePort {

    private final String GROUP_INFO = "group_info";
    private final MongoTemplate mongoTemplate;

    @Override
    public List<String> getAllGroupMember(long channelId) {
        Query query = new Query(Criteria.where("_id").is(channelId));
        ChannelMembers channelMembers = mongoTemplate.findOne(query, ChannelMembers.class, GROUP_INFO);
        return channelMembers.getMembers();
    }
}
