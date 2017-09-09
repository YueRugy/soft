package com.yue.web;

import com.yue.entity.User;
import com.yue.enums.Code;
import com.yue.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "用户注册", notes = "根据提交来的user对象写入表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "user实体", required = true, dataType = "User"),
            @ApiImplicitParam(name = "userType", value = "user 类型", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Integer userType) {
        return dataJson(userService.register(user, userType), Code.SUCCESS.getCode());
    }

}
