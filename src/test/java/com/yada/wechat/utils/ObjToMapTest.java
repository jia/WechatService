package com.yada.wechat.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjToMapTest {
    @Test
    public void objMap() throws Exception {

        User user = new User("xiaoming","1");

        Map<String,String> map = ObjToMap.objMap(user);

        System.out.println(map);

        Assert.assertEquals("1",map.get("id"));
    }

}