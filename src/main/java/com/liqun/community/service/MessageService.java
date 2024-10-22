package com.liqun.community.service;

import com.liqun.community.dao.MessageMapper;
import com.liqun.community.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.service
 * @className: MessageServer
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/23 0:16
 */
@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    //查询当前用户的会话列表,针对每个会话只返回一条最新的私信
    public List<Message> selectConversations(int userId, int offset, int limit) {
        return messageMapper.selectConversations(userId, offset, limit);
    }
    //查询当前用户的会话数量
    public int selectConversationCount(int userId) {
        return messageMapper.selectConversationCount(userId);
    }
    //查询某个会话所包含的私信列表
    public List<Message> selectLetters(String conversationId, int offset, int limit) {
        return messageMapper.selectLetters(conversationId, offset, limit);
    }
    //查询某个会话所包含的私信数量
    public int selectLetterCount(String conversationId) {
        return messageMapper.selectLetterCount(conversationId);
    }
    //查询未读私信的数量
    public int selectLetterUnreadCount(int userId, String conversationId) {
        return messageMapper.selectLetterUnreadCount(userId, conversationId);
    }

}
