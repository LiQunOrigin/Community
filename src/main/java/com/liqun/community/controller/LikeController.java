package com.liqun.community.controller;

import com.liqun.community.entity.User;
import com.liqun.community.service.LikeService;
import com.liqun.community.util.CommunityUtil;
import com.liqun.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.controller
 * @className: LikeController
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/23 10:58
 */
@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(path = "/like", method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId) {
        User user = hostHolder.getUser();

        //点赞
        likeService.like(user.getId(), entityType, entityId);
        //返回点赞数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        //返回点赞状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        //返回结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);
        return CommunityUtil.getJSONString(0, null, map);
    }
}
