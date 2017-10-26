package com.yada.wechat.contorller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    private CustomerController customerController;

    @Test
    public void unifiedOrder() throws Exception {
        //tood
    }

//    @Test
//    public void orderQuery() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/orderQuery"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void refund() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/refund"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void payNotify() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/pay_notify"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void refundNotify() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/refund_notify"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

}