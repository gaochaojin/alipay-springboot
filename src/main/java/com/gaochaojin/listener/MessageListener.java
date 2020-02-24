package com.gaochaojin.listener;

import com.gaochaojin.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 17:05 2020/2/20
 */
@Slf4j
@Component
public class MessageListener {

    @Autowired
    OrderService orderService;

    @RabbitListener(queues = "jack.message.response")
    public void process(final String result) {
        log.info("=======接收到余额宝转账成功的应答消息=========" + result);
        orderService.updateMessage(result);
    }

}
