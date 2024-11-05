package com.liqun.community.controller;

import com.liqun.community.entity.DiscussPost;
import com.liqun.community.entity.Page;
import com.liqun.community.service.DiscussPostService;
import com.liqun.community.service.ElasticsearchService;
import com.liqun.community.service.LikeService;
import com.liqun.community.service.UserService;
import com.liqun.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
 * @className: SearchController
 * @author: LiQun
 * @description: TODO
 * @data 2024/11/5 15:12
 */
@Controller
public class SearchController implements CommunityConstant {
    @Autowired
    public ElasticsearchService elasticsearchService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    @Autowired
    private DiscussPostService discussPostService;

    @RequestMapping(path="/search",method = RequestMethod.GET)
    public String search(String keyword, Page page, Model model){
        //搜索帖子
        org.springframework.data.domain.Page<DiscussPost> searchResult =
                elasticsearchService.searchDiscussPost(keyword, page.getCurrent(), page.getLimit());
        //聚合数据
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if(searchResult != null){
            for(DiscussPost post : searchResult){
                Map<String, Object> map = new HashMap<>();
                //帖子
                map.put("post", post);
                //作者
                map.put("user", userService.findUserById(post.getUserId()));
                //点赞数量
                map.put("likeCount", likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId()));
                discussPosts.add(map);
            }
        }

        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("keyword", keyword);

        page.setPath("/search?keyword=" + keyword);
        page.setRows(searchResult == null ? 0 : (int) searchResult.getTotalElements());

        return "/site/search";
    }
}
