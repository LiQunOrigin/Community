package com.liqun.community.entity;

import lombok.Data;

import java.util.Map;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.entity
 * @className: Event
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/30 10:19
 */
public class Event {
    private String topic;
    private int userId;
    private int entityType;
    private int entityId;
    private int entityUserId;
    private Map<String, Object> data;

    public String getTopic() {
        return topic;
    }

    public int getUserId() {
        return userId;
    }

    public int getEntityType() {
        return entityType;
    }

    public int getEntityId() {
        return entityId;
    }

    public int getEntityUserId() {
        return entityUserId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public Event setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public Event setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public Event setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public Event setEntityUserId(int entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Event setData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
