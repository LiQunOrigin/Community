package com.liqun.community.util;

public interface CommunityConstant {
    //激活成功
    int ACTIVATION_SUCCESS = 0;
    //重复激活
    int ACTIVATION_REPEAT = 1;
    //激活失败
    int ACTIVATION_FAILURE = 2;

    //默认状态的登录凭证的超时时间
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;
    //记住状态的登录凭证超时时间
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
    //实体类型:帖子
    int ENTITY_TYPE_POST = 1;
    //实体类型:评论
    int ENTITY_TYPE_COMMENT = 2;
    //系统通知
    String SYSTEM_NOTIFICATION = "system";
    //主题:评论
    String TOPIC_COMMENT = "comment";
    //主题:点赞
    String TOPIC_LIKE = "like";
    //主题:关注
    String TOPIC_FOLLOW = "follow";
    //主题:发帖
    String TOPIC_PUBLISH = "publish";
    //主题:删帖
    String TOPIC_DELETE = "delete";

    //主题:分享
    String TOPIC_SHARE = "share";
    //主题:收藏
    String TOPIC_COLLECT = "collect";
    //主题:取消收藏
    String TOPIC_UNCOLLECT = "uncollect";

}
