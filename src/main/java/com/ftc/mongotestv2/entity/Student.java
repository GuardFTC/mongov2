package com.ftc.mongotestv2.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 冯铁城 [17615007230@163.com]
 * @date 2022-07-20 19:12:01
 * @describe: MongoDB实体类
 * <p>
 * “@Document(collection = "student")” 用于指定对应哪个集合
 */
@Data
@Document(collection = "student")
public class Student {

    private String id;

    private String name;

    private int age;
}