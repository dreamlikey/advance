package com.wdq.mq;

import com.wdq.SpringBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @Author: wudq
 * @Date: 2018/11/1
 */
@SpringBootTest(classes= SpringBootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMQ {

    @Autowired
    MqProvider mqProvider;

    @Test
    public  void  testSend() {
        mqProvider.send();
    }

}