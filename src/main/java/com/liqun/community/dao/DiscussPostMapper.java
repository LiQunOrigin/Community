package com.liqun.community.dao;

import com.liqun.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //分页查询
    //offset:起始位置
    //limit:每页显示的条数
    List<DiscussPost>  selectDiscussPosts(int userId , int offset, int limit);

    //查询总条数,用于分页
    //@Param用于给参数起别名
    //如果只有一个参数,并且在<if>里用,必须起别名
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);
}
