package com.yada.wechat.web;

import com.yada.wechat.service.WechatService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Type;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WechatControllerTest {

    @Autowired
    private MockMvc mvc;

//    @Autowired
//    WebApplicationContext webApplicationConnect;
//
//    @Autowired
//    WechatService wechatService;
//
//    @Before
//    public void setUp() {
//        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();
//
//    }

//    @Test
//    public void test1() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/test"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }


    @Test
    public void orderQuery() throws Exception {

        OrderQuery orderQuery = new OrderQuery("2016090910595900000012");
        mvc.perform(MockMvcRequestBuilders.post("/orderQuery",orderQuery))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
//
//    @MockBean
//    private WechatService wechatService;
//
//
//    @Test
//    public void payNotify() throws Exception {
//
////        PayNotify payNotify = new PayNotify()
//        mvc.perform(MockMvcRequestBuilders.post("/pay_notify"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
//
//    @Test
//    public void refundNotify() throws Exception {
//    }

}