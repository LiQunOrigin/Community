package com.liqun.community.controller;

import com.liqun.community.entity.DiscussPost;
import com.liqun.community.entity.Page;
import com.liqun.community.entity.User;
import com.liqun.community.service.DiscussPostService;
import com.liqun.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.controller
 * @className: HomeController
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/17 20:41
 */
@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path="/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法调用之前,springmvc会自动实例化Model和Page,并将Page注入Model,
        // 所以可以直接用
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");


        //方法调用链：DiscussPostMapper->DiscussPostService->HomeController
        //controller只负责调用service,不负责查询数据库
        //model:模型,用来存放数据,最终传递给页面
        List<DiscussPost> list = discussPostService
                .findDiscussPosts(0,page.getOffset(),page.getLimit());
        List<Map<String,Object>> discussPosts = new ArrayList<>();


        if(list != null){
            for(DiscussPost post:list){
                Map<String,Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
