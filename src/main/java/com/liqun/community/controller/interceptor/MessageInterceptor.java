package com.liqun.community.controller.interceptor;

import com.liqun.community.service.MessageService;
import com.liqun.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.controller.interceptor
 * @className: MessageInterceptor
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/30 22:28
 */
@Component
public class MessageInterceptor implements HandlerInterceptor {
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private MessageService messageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (hostHolder.getUser() != null) {
            int letterUnreadCount = messageService.selectLetterUnreadCount(hostHolder.getUser().getId(), null);
            int noticeUnreadCount = messageService.selectNoticeUnreadCount(hostHolder.getUser().getId(), null);
            request.setAttribute("allUnreadCount", letterUnreadCount + noticeUnreadCount);
        }
        return true;
    }
}
