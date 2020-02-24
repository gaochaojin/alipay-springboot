package com.gaochaojin.timer;

import com.alibaba.fastjson.JSONObject;
import com.gaochaojin.bean.Message;
import com.gaochaojin.dao.MessageMapper;
import com.gaochaojin.rabbitmq.RabbitmqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:25 2020/2/20
 */
@Slf4j
@Component
public class ScheduleService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    RabbitmqSender rabbitmqSender;

    @Scheduled(cron = "0/5 0/1 * * * ?")
    public void scheduled() {
        log.info("========使用cron {}", System.currentTimeMillis());
        List<Message> messages = messageMapper.queryMessageByState("unconfirm");
        log.info("========messages:" + JSONObject.toJSONString(messages));
        for (Message message : messages) {
            log.info("=======定时器往rabbitmq发送消息:" + JSONObject.toJSONString(message));
            rabbitmqSender.sendMessage("exchange.message", "jack.message.routeKey", message);
        }
    }

}
