package com.liqun.community.event;

import com.alibaba.fastjson.JSONObject;
import com.liqun.community.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.event
 * @className: EventProducer
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/30 10:26
 */
@Component
public class EventProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    //处理事件
    public void fireEvent(Event event){
        //将事件发布到指定的主题
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}
