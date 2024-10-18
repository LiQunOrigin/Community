package com.liqun.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.entity
 * @className: DiscussPost
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/17 19:31
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscussPost {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int type;
    private int status;
    private int commentCount;
    private double score;
    private Date createTime;

}
