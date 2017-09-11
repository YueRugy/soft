package com.yue.web;

import com.yue.entity.User;
import com.yue.enums.Code;
import com.yue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by yue on 2017/9/9
 */
@RestController
@RequestMapping("user")
public class UserController extends AbstractController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Integer userType) {
        return dataJson(userService.register(user, userType), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, HttpServletResponse response) {
        return dataJson(userService.login(user,response), Code.SUCCESS.getCode());
    }


}
