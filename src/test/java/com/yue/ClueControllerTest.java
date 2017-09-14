package com.yue;

import com.yue.service.ClueService;
import com.yue.web.ClueController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by yue on 2017/9/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClueControllerTest {
    private MockMvc mvc;
    @Autowired
    private ClueService clueService;
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new ClueController(clueService)).setCustomArgumentResolvers(pageableArgumentResolver).build();
        ;
    }


    @Test
    public void testRecommend() throws Exception {
        RequestBuilder requestBuilder = post("/clue/")
                .param("name", "yue")
                .param("phone", "18058798506")
                .param("title", "有意向");
        //mvc.perform(requestBuilder).andExpect(status().isOk());

        // System.out.println(content().string());
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testGetRecommendByPage() throws Exception {
        RequestBuilder requestBuilder = post("/clue/1");
        //mvc.perform(requestBuilder).andExpect(status().isOk());

        // System.out.println(content().string());
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }
}
