package com.liqun.community.service;

import com.liqun.community.dao.UserMapper;
import com.liqun.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.service
 * @className: UserService
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/17 20:36
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
