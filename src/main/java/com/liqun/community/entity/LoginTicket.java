package com.liqun.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.entity
 * @className: LoginTicket
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/19 22:42
 */
@Data
@ToString
public class LoginTicket {

    private int id;
    private int userId;
    private String ticket;
    private int status;
    private Date expired;
    // 0-有效 1-无效

}
