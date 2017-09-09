package com.yue;

import com.yue.service.UserService;
import com.yue.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by yue on 2017/9/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class UserControllerTest {
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
    }

    @Test
    public void test() throws Exception {

        RequestBuilder requestBuilder = post("/user/register/").param("phone", "180587985");
        mvc.perform(requestBuilder).andExpect(status().isOk());
    }


}
