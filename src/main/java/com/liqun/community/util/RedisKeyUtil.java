package com.liqun.community.util;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.util
 * @className: RedisKeyUtil
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/23 10:52
 */
public class RedisKeyUtil {
    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";

    // 某个实体的赞
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    private static final String PREFIX_USER_LIKE = "like:user";

    // 某个用户的赞
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    private static final String PREFIX_FOLLOWEE = "followee";
    private static final String PREFIX_FOLLOWER = "follower";

    // 某个用户关注的实体
    // followee:userId:entityType -> zset(entityId, now)
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    // 某个实体拥有的粉丝
    // follower:entityType:entityId -> zset(userId, now)
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    private static final String PREFIX_KAPTCHA = "kaptcha";//验证码

    // 验证码
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + SPLIT + owner;
    }
    private static final String PREFIX_TICKET = "ticket";//凭证
    // 登录凭证的ticket
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }
    private static final String PREFIX_USER = "user";//用户
    // 在Redis中存取用户
    public static String getUserKey(int userId) {
        return PREFIX_USER + SPLIT + userId;
    }
    private static final String PREFIX_UV = "uv";
    private static final String PREFIX_DAU = "dau";
}
