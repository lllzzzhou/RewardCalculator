package com.example.rewardscalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestController {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext wac;

    @Test
    public void testController() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        RequestBuilder request;
        request = MockMvcRequestBuilders.get("http://127.0.0.1:8080/customers/1");
        String res = mvc.perform(request).andReturn().getResponse().getContentAsString();
        assertEquals("{\"customerId\":1,\"lastFirstMonthRewards\":72,\"lastSecondMonthRewards\":38,\"lastThirdMonthRewards\":0,\"totalRewards\":110}", res);
    }
}
