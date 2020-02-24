package com.gaochaojin.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.gaochaojin.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:55 2020/2/20
 */
@Slf4j
@Component
public class RabbitmqSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String exchange, String routeKey, Message message) {

        log.info("=======发送消息余额宝=========" + message);
        amqpTemplate.convertAndSend(exchange, routeKey, JSONObject.toJSONString(message));

    }
}

