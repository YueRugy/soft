package com.yue;

import com.yue.entity.ProductTag;
import com.yue.service.AreaService;
import com.yue.service.ProductTagService;
import com.yue.web.AreaController;
import com.yue.web.BProductTagController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by yue on 2017/9/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BProductTagControllerTest {
    private MockMvc mvc;


    @Autowired
    private ProductTagService productTagService;
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;


    @Before
    public void setUp() {
        // mvc=webAppContextSetup(get())
        mvc = MockMvcBuilders.standaloneSetup(new BProductTagController(productTagService)).setCustomArgumentResolvers(pageableArgumentResolver).build();
    }

    @Test
    public void testAdd() throws Exception {
        RequestBuilder requestBuilder = post("/back/productTag/").param("name", "电子1");
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testUpdate() throws Exception {
        RequestBuilder requestBuilder = put("/back/productTag/3").param("name", "电子2");
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testDelete() throws Exception {
        RequestBuilder requestBuilder = delete("/back/productTag/3");
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testGetAll() throws Exception {
        RequestBuilder requestBuilder = get("/back/productTag/");
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

}
