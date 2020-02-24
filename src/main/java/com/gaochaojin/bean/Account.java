package com.gaochaojin.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:13 2020/2/20
 */
@Data
public class Account {

    private String userId;

    private Integer amount;

    private Date updateTime;

}
