package com.liqun.community.controller;

import com.liqun.community.entity.DiscussPost;
import com.liqun.community.entity.User;
import com.liqun.community.service.DiscussPostService;
import com.liqun.community.util.CommunityUtil;
import com.liqun.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.controller
 * @className: DiscussPostController
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/22 10:59
 */
@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    public DiscussPostService discussPostService;

    @Autowired
    public HostHolder hostHolder;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJSONString(403, "你还没有登录哦！");
        }
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        // 报错的情况，将来统一处理
        return CommunityUtil.getJSONString(0, "发布成功！");
    }
}
