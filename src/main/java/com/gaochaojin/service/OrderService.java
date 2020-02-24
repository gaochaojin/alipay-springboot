package com.gaochaojin.service;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:23 2020/2/20
 */
public interface OrderService {

    public void updateAmount(int amount, String userId, int messageId);

    public void updateMessage(String param);
}
