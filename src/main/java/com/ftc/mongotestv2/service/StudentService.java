package com.ftc.mongotestv2.service;

import com.ftc.mongotestv2.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:冯铁城 [17615007230@163.com]
 * @date:2022-07-27 15:52:12
 * @describe:
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    private final MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void saveStudent() {

        //1.创建对象
        Student student = new Student();
        student.setId("1");

        //2.保存
        mongoTemplate.insert(student);

        //3.执行异常
        int error = 1 / 0;
    }
}
