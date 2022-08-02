package com.ftc.mongotestv2.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Test
    void testPut() {

        //1.获取文件输入流
        BufferedInputStream inputStream = FileUtil.getInputStream("C:\\Users\\86176\\Desktop\\OIP-C.jpg");

        //2.设置文件备注
        HashMap<String, Object> metadata = MapUtil.newHashMap(2);
        metadata.put("作者", "ftc");
        metadata.put("作用", "学习使用");

        //3.存入文件
        ObjectId store = gridFsTemplate.store(inputStream, "test.jpg", metadata);
        Assert.isTrue(ObjectUtil.isNotNull(store));
    }

    @Test
    void testGet() throws IOException {

        //1.获取文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("metadata.作用").regex("学")));

        //2.获取文件流
        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);

        //3.文件写入
        InputStream inputStream = resource.getInputStream();
        byte[] bytes = IoUtil.readBytes(inputStream);
        File file = FileUtil.writeBytes(bytes, "C:\\Users\\86176\\Desktop\\copy.jpg");
        Assert.isTrue(file.exists());
    }

    @Test
    void testList() {

        //1.获取文件
        GridFSFindIterable gridFSFiles = gridFsTemplate.find(new Query());

        //2.循环遍历
        for (GridFSFile gridFSFile : gridFSFiles) {
            System.out.println(gridFSFile.getFilename());
        }
    }

    @Test
    void testDelete() {

        //1.删除文件
        gridFsTemplate.delete(new Query());

        //2.验证
        GridFSFindIterable gridFSFiles = gridFsTemplate.find(new Query());
        Assert.isTrue(CollUtil.isEmpty(gridFSFiles));
    }
}