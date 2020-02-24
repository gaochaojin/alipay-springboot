package com.gaochaojin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.gaochaojin"})
@MapperScan(basePackages = {"com.gaochaojin.dao"})
@EnableRabbit
@EnableScheduling
public class AlipaySpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlipaySpringbootApplication.class, args);
    }

}
