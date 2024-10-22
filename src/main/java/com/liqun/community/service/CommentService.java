package com.liqun.community.service;

import com.liqun.community.dao.CommentMapper;
import com.liqun.community.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.service
 * @className: CommentService
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/22 17:31
 */

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit){
        return commentMapper.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    public int selectCountByEntityId(int entityType, int entityId){
        return commentMapper.selectCountByEntityId(entityType, entityId);
    }

    public int findCommentCount(int entityTypeComment, int id) {
        return commentMapper.selectCountByEntityId(entityTypeComment, id);
    }
}
