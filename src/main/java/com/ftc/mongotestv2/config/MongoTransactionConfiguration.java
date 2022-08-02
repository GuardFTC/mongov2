package com.ftc.mongotestv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2022-07-27 15:49:00
 * @describe: mongo事务监听器
 */
@Configuration
public class MongoTransactionConfiguration {

    @Bean
    MongoTransactionManager mongoTransactionManager(SimpleMongoClientDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }
}
