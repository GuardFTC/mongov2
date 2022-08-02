package com.ftc.mongotestv2.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.annotation.Resource;

/**
 * @author 冯铁城 [17615007230@163.com]
 * @date 2022-07-20 19:26:10
 * @describe: mongodb监听器
 */
@Configuration
public class MongoReadyListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * spring-data-mongo会默认为每个文档添加的键，值为文档对应类的包路径，例如：com.ftc.redistry.mongo.Student
     */
    private static final String TYPEKEY = "_class";

    @Resource
    MongoTemplate oneMongoTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        MongoConverter converter = oneMongoTemplate.getConverter();
        if (converter.getTypeMapper().isTypeKey(TYPEKEY)) {
            ((MappingMongoConverter) converter).setTypeMapper(new DefaultMongoTypeMapper(null));
        }
    }
}
