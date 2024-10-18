package com.liqun.community.service;

import com.liqun.community.dao.DiscussPostMapper;
import com.liqun.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.service
 * @className: DIscussPostService
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/17 20:14
 */
@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }
    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
