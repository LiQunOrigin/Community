package com.liqun.community.service;

import com.liqun.community.dao.MessageMapper;
import com.liqun.community.entity.Message;
import com.liqun.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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

    @Autowired
    private SensitiveFilter sensitiveFilter;

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

    //新增消息
    public int insertMessage(Message message) {
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        message.setContent(sensitiveFilter.filter(message.getContent()));
        return messageMapper.insertMessage(message);
    }

    public int readMessage(List<Integer> ids) {
        return messageMapper.updateStatus(ids, 1);
    }

    //查询某个主题下最新的通知
    public Message selectLatestNotice(int userId, String topic) {
        return messageMapper.selectLatestNotice(userId, topic);
    }

    //查询某个主题所包含的通知数量
    public int selectNoticeCount(int userId, String topic) {
        return messageMapper.selectNoticeCount(userId, topic);
    }
    //查询未读的通知数量
    public int selectNoticeUnreadCount(int userId, String topic) {
        return messageMapper.selectNoticeUnreadCount(userId, topic);
    }
    //查询某个主题所包含的通知列表
    public List<Message> selectNotices(int userId, String topic, int offset, int limit) {
        return messageMapper.selectNotices(userId, topic, offset, limit);
    }
}
