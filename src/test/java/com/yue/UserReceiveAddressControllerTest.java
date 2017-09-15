package com.yue;

import com.yue.service.UserReceiveAddressService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * Created by yue on 2017/9/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserReceiveAddressControllerTest {
    private MockMvc mvc;


    @Autowired
    private UserReceiveAddressService userReceiveAddressService;
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Before
    public void setUp() {
        // mvc=webAppContextSetup(get())
        mvc = MockMvcBuilders.standaloneSetup(new UserReceiveAddressController(userReceiveAddressService)).setCustomArgumentResolvers(pageableArgumentResolver).build();
    }

    @Test
    public void testAdd() throws Exception {
        RequestBuilder requestBuilder = post("/phone/userReceiveAddress/").
                param("provinceId", "330000").
                param("cityId", "330100").
                param("districtId", "330108")
                .param("address", "西兴街道春风集团").
                        param("contact", "yue").param("contactPhone", "18058798506");

        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }


    @Test
    public void testUpdate() throws Exception {
        RequestBuilder requestBuilder = put("/phone/userReceiveAddress/1").
                param("provinceId", "330000").
                param("cityId", "330100").
                param("districtId", "330108")
                .param("address", "西兴街道春风集团12").
                        param("contact", "yue22").param("contactPhone", "18058798506");

        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testDelete() throws Exception {
        RequestBuilder requestBuilder = delete("/phone/userReceiveAddress/1");

        System.out.println(mvc.perform(requestBuilder).andReturn().getResponse().getContentAsString());
    }
}
