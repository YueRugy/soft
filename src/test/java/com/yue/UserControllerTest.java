package com.yue;

import com.yue.service.UserService;
import com.yue.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).setCustomArgumentResolvers(pageableArgumentResolver).build();
    }

    @Test
    public void test() throws Exception {

        RequestBuilder requestBuilder = post("/user/register/")
                .param("phone", "18058798508")
                .param("password", "000000")
                .param("openId", "123")
                .param("userType", "1");
        mvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void loginTest() throws Exception {
        RequestBuilder requestBuilder = post("/user/login/").param("phone", "18058798506")
                .param("password", "000000").param("openId", "123456");
        mvc.perform(requestBuilder).andExpect(status().isOk());

    }

    @Test
    public void weLoginTest() throws Exception {
        RequestBuilder requestBuilder = post("/user/weLogin/")
                .param("openId", "oYjnvsmPGn6_-DakiC8QN3uFrBAg");
        mvc.perform(requestBuilder).andExpect(status().isOk());

    }

    @Test
    public void getAllTest() throws Exception {
        RequestBuilder requestBuilder = get("/user/")
                .param("openId", "123456").param("pageNo", "1").param("pageSize", "10");
        mvc.perform(requestBuilder).andExpect(status().isOk());

        // System.out.println(content().string());
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testRecommend() throws Exception {
        RequestBuilder requestBuilder = get("/user/getUserRecommendContacts");
        mvc.perform(requestBuilder).andExpect(status().isOk());

        // System.out.println(content().string());
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testDetailUserInfo() throws Exception {
        RequestBuilder requestBuilder = get("/user/3");
        //mvc.perform(requestBuilder).andExpect(status().isOk());

        // System.out.println(content().string());
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }
}
