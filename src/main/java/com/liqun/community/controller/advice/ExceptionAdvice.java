package com.liqun.community.controller.advice;

import com.alibaba.fastjson.JSON;
import com.liqun.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.controller.advice
 * @className: ExceptionAdvice
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/23 9:08
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        {
            logger.error("服务器发生异常: ", e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                logger.error(element.toString());
            }

            String xRequestWith = request.getHeader("x-requested-with");
            if ("XMLHttpRequest".equals(xRequestWith)) {
                //如果是异步请求
                response.setContentType("application/plain;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(CommunityUtil.getJSONString(1, "服务器发生异常"));
            } else {
                //如果是普通请求
                response.sendRedirect(request.getContextPath() + "/error");
            }
        }

    }
}
