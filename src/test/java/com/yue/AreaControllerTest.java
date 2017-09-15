package com.yue;

import com.yue.service.AreaService;
import com.yue.service.UserReceiveAddressService;
import com.yue.web.AreaController;
import com.yue.web.UserReceiveAddressController;
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

/**
 * Created by yue on 2017/9/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaControllerTest {
    private MockMvc mvc;


    @Autowired
    private AreaService areaService;
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Before
    public void setUp() {
        // mvc=webAppContextSetup(get())
        mvc = MockMvcBuilders.standaloneSetup(new AreaController(areaService)).setCustomArgumentResolvers(pageableArgumentResolver).build();
    }

    @Test
    public void test() throws Exception {
        RequestBuilder requestBuilder = get("/area/0");
        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

}
