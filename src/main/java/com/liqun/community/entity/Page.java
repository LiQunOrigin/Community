package com.liqun.community.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.entity
 * @className: Page
 * @author: LiQun
 * @description: 封装分页相关信息
 * @data 2024/10/17 21:10
 */
@Data
@ToString
public class Page {
    //当前页码
    private int current = 1;
    //显示上限
    private int limit = 10;
    //数据总数(用于计算总页数)
    private int rows;
    //查询路径(用于复用分页链接)
    private String path;

    //获取当前页的起始行
    public int getOffset() {
        return (current - 1) * limit;
    }
    //判断当前页码是否为首页
    public int getTotal() {
        if(rows % limit == 0) {
            return rows / limit;
        } else {
            return rows / limit + 1;
        }
    }
    //判断当前页码是否为尾页
    public int getFrom() {
        int from = current - 2;
        return Math.max(from, 1);
    }
    //获取结束页码
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return Math.min(to, total);
    }
}
