package com.yue;

import com.yue.service.ProductTypeService;
import com.yue.web.ProductTypeController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by yue on 2017/9/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTypeControllerTest {
    private MockMvc mvc;
    @Autowired
    private ProductTypeService productTypeService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new ProductTypeController(productTypeService)).build();
    }

    @Test
    public void test() throws Exception {
        RequestBuilder requestBuilder = post("/productType/back")
                .param("typeName", "电子")
                .param("pid", "0");
        mvc.perform(requestBuilder).andExpect(status().isOk());

        requestBuilder = get("/productType//back/0");
        mvc.perform(requestBuilder).andExpect(status().isOk());
        requestBuilder = put("/productType//back/6").param("typeName", "电子商品1");
        mvc.perform(requestBuilder).andExpect(status().isOk());
        requestBuilder = delete("/productType/back/6");
        mvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void testAdd() throws Exception {
        RequestBuilder requestBuilder = post("/productType/back/")
                .param("typeName", "户外278")
                .param("pid", "0");
        mvc.perform(requestBuilder).andExpect(status().isOk());

    }


    @Test
    public void testSelect() throws Exception {
        RequestBuilder requestBuilder = get("/productType/1");
        mvc.perform(requestBuilder).andExpect(status().isOk());

        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());

    }


    /**
     * Created by yue on 2017/9/14.
     */
    public static class ClueControllerTest {
    }
}
