package com.liqun.community.controller;

import com.liqun.community.entity.Message;
import com.liqun.community.entity.Page;
import com.liqun.community.entity.User;
import com.liqun.community.service.MessageService;
import com.liqun.community.service.UserService;
import com.liqun.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
 * @className: MessageController
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/23 0:18
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    //私信列表
    @RequestMapping(path = "/letter/list", method = RequestMethod.GET)
    public String getLetterList(Model model, Page page) {
        User user = hostHolder.getUser();
        //查询会话列表
        //分页信息
        page.setLimit(5);
        page.setPath("/letter/list");
        page.setRows(messageService.selectConversationCount(user.getId()));
        //会话列表
        List<Message> conversationList = messageService.selectConversations(user.getId(), page.getOffset(), page.getLimit());
        List<Map<String, Object>> conversation = new ArrayList<>();
        if (conversationList != null) {
            for (Message message : conversationList) {
                Map<String, Object> map = new HashMap<>();
                map.put("conversation", message);
                map.put("letterCount", messageService.selectLetterCount(message.getConversationId()));
                map.put("unreadCount", messageService.selectLetterUnreadCount(user.getId(), message.getConversationId()));
                int targetId = user.getId() == message.getFromId() ? message.getToId() : message.getFromId();
                map.put("target", userService.findUserById(targetId));
                conversation.add(map);
            }
        }
        model.addAttribute("conversations", conversation);
        //查询未读消息数量
        int letterUnreadCount = messageService.selectLetterUnreadCount(user.getId(), null);
        model.addAttribute("letterUnreadCount", letterUnreadCount);

        return "/site/letter";
    }

    //私信详情
    @RequestMapping(path = "/letter/detail/{conversationId}", method = RequestMethod.GET)
    public String getLetterDetail(@PathVariable("conversationId") String conversationId, Page page, Model model) {
        //分页信息
        page.setLimit(5);
        page.setPath("/letter/detail/" + conversationId);
        page.setRows(messageService.selectLetterCount(conversationId));
        //私信列表
        List<Message> letterList = messageService.selectLetters(conversationId, page.getOffset(), page.getLimit());
        List<Map<String, Object>> letters = new ArrayList<>();
        if (letterList != null) {
            for (Message message : letterList) {
                Map<String, Object> map = new HashMap<>();
                map.put("letter", message);
                map.put("fromUser", userService.findUserById(message.getFromId()));
                letters.add(map);
            }
        }
        model.addAttribute("letters", letters);
        //私信目标
        model.addAttribute("target", getLetterTarget(conversationId));
        return "/site/letter-detail";

    }

    private User getLetterTarget(String conversationId) {
        String[] ids = conversationId.split("_");
        int id0 = Integer.parseInt(ids[0]);
        int id1 = Integer.parseInt(ids[1]);
        if (hostHolder.getUser().getId() == id0) {
            return userService.findUserById(id1);
        } else {
            return userService.findUserById(id0);
        }
    }
}
