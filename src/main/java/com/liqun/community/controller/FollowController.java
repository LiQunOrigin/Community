package com.liqun.community.controller;

import com.liqun.community.entity.User;
import com.liqun.community.service.FollowService;
import com.liqun.community.util.CommunityConstant;
import com.liqun.community.util.CommunityUtil;
import com.liqun.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.controller
 * @className: FollowController
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/24 16:59
 */
@Controller
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private HostHolder hostHolder;
    //关注
    @RequestMapping(path = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public String follow(int entityType, int entityId) {
        User user = hostHolder.getUser();
        followService.follow(user.getId(), entityType, entityId);
        return CommunityUtil.getJSONString(0, "已关注!");
    }

    //取消关注
    @RequestMapping(path = "/unfollow", method = RequestMethod.POST)
    @ResponseBody
    public String unfollow(int entityType, int entityId) {
        User user = hostHolder.getUser();
        followService.unfollow(user.getId(),entityType, entityId);
        return CommunityUtil.getJSONString(0, "已取消关注!");
    }

}
