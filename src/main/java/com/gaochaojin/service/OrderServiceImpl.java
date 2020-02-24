package com.gaochaojin.service;

import com.alibaba.fastjson.JSONObject;
import com.gaochaojin.bean.Account;
import com.gaochaojin.bean.Message;
import com.gaochaojin.dao.AccountMapper;
import com.gaochaojin.dao.MessageMapper;
import com.gaochaojin.rabbitmq.RabbitmqConfig;
import com.gaochaojin.rabbitmq.RabbitmqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:24 2020/2/20
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    RabbitmqSender rabbitmqSender;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Override
    public void updateAmount(int amount, String userId, int messageId) {
        int row = (int) transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                // 操作支付宝的本地account账单表
                Account account = new Account();
                account.setAmount(amount);
                account.setUserId(userId);
                int count = accountMapper.updateAccount(account);
                if (count == 1) {
                    Message message = new Message();
                    message.setUserId(userId);
                    message.setAmount(amount);
                    message.setMessageId(messageId);
                    return messageMapper.addMessage(message);
                }
                return 0;
            }
        });

        if (row == 1) {
            // 如果消息表操作成功，往mq里面插入消息
            Message message = new Message();
            message.setAmount(amount);
            message.setUserId(userId);
            message.setMessageId(messageId);
            message.setState("unconfirm");
            rabbitmqSender.sendMessage("exchange.message", "jack.message.routeKey", message);
        }
    }

    @Override
    public void updateMessage(String param) {
        JSONObject jsonObject = JSONObject.parseObject(param);
        int messageId = jsonObject.getInteger("messageId");
        Message message = new Message();
        message.setState("confirm");
        message.setMessageId(messageId);
        int i = messageMapper.updateMessage(message);
    }
}
