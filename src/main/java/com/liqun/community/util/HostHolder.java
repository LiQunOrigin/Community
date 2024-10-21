package com.liqun.community.util;

import com.liqun.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.util
 * @className: HostHolder
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/21 10:22
 */
// 持有用户信息，用于代替session对象
@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<>();
    public void setUser(User user){
        users.set(user);
    }
    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}
