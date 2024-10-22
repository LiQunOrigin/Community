package com.liqun.community.dao;

import com.liqun.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 查询评论数量
    int selectCountByEntityId(int entityType, int entityId);

    // 查询评论
    List<Comment> selectCommentsByEntity(int entityType, int entityId,int offset, int limit);

    // 添加评论

}
