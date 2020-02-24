package com.gaochaojin.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:14 2020/2/20
 */
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 1;

    private int messageId;

    private String userId;

    private int amount;

    private String state;
}
