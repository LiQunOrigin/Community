package com.liqun.community.service;

import com.liqun.community.dao.CommentMapper;
import com.liqun.community.entity.Comment;
import com.liqun.community.util.CommunityConstant;
import com.liqun.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

import static com.liqun.community.util.CommunityConstant.ENTITY_TYPE_POST;

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
public class CommentService implements CommunityConstant {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private DiscussPostService discussPostService;
    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit){
        return commentMapper.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    public int selectCountByEntityId(int entityType, int entityId){
        return commentMapper.selectCountByEntityId(entityType, entityId);
    }

    public int findCommentCount(int entityTypeComment, int id) {
        return commentMapper.selectCountByEntityId(entityTypeComment, id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addComment(Comment comment){
        if(comment == null){
            throw new IllegalArgumentException("参数不能为空");
        }

        //添加评论
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        int rows = commentMapper.insertComment(comment);

        //更新帖子评论数量
        if(comment.getEntityType() == ENTITY_TYPE_POST){
            int count = commentMapper.selectCountByEntityId(ENTITY_TYPE_POST, comment.getEntityId());
            discussPostService.updateCommentCount(comment.getEntityId(), count);
        }
        return rows;
    }
}
