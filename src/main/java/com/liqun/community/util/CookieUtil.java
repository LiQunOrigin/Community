package com.liqun.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.util
 * @className: CookieUtil
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/21 9:55
 */
public class CookieUtil {
    public static String getValue(HttpServletRequest request, String name){
        if (request == null || name == null)
            throw new IllegalArgumentException("参数为空");
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie.getValue();
        }
        return null;
    }
}
