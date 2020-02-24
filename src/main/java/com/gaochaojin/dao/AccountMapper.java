package com.gaochaojin.dao;

import com.gaochaojin.bean.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:17 2020/2/20
 */
public interface AccountMapper {

    @Update("update t_account set amount = amount - #{amount},update_time = now() where user_id = #{userId}")
    int updateAccount(Account account);

    @Insert("insert t_account(user_id,amount,update_time) values (#{userId},#{amount},now())")
    int addAcount(Account account);
}
