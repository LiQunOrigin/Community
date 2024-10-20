package com.liqun.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.entity
 * @className: User
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/17 15:57
 */
@Data
@ToString
public class User {

    private int id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private int type;
    private int status;
    private String activationCode;
    private String headerUrl;
    private Date createTime;
}
