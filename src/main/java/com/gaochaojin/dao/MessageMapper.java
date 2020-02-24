package com.gaochaojin.dao;

import com.gaochaojin.bean.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: gaochaojin
 * @Email: gaochaojin@163.com
 * @Description:
 * @Date Created in 15:21 2020/2/20
 */
public interface MessageMapper {

    @Update("update t_message set state = #{state} where message_id = #{messageId}")
    int updateMessage(Message message);

    @Insert("insert into t_message(user_id,message_id,amount,state) values(#{userId},#{messageId},#{amount},'unconfirm')")
    int addMessage(Message message);

    @Select("select message_id as messageId,user_id as userId,amount,state from t_message where state = #{state}")
    List<Message> queryMessageByState(String state);
}
