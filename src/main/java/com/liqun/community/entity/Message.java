package com.liqun.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.entity
 * @className: Message
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/22 23:54
 */
@Data
@ToString
public class Message {

    private int id;
    private int fromId;
    private int toId;
    private String conversationId;
    private String content;
    private int status;
    private Date createTime;
}
