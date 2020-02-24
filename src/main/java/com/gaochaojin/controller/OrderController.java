package com.gaochaojin.controller;

import com.gaochaojin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:59 2020/2/20
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/transfer")
    public String transferAmount(String userId, int messageId, int amount) {
        try {
            orderService.updateAmount(amount, userId, messageId);
        } catch (Exception e) {
            return "fall";
        }
        return "ok";
    }

    @GetMapping("/callback")
    public String callback(String param) {
        try {
            orderService.updateMessage(param);
        } catch (Exception e) {
            return "fail";
        }
        return "ok";
    }
}
