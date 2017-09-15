package com.yue.web;

import com.yue.entity.UserReceiveAddress;
import com.yue.enums.Code;
import com.yue.service.UserReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yue on 2017/9/15
 */
@RestController
@RequestMapping("/phone/userReceiveAddress")
public class UserReceiveAddressController extends AbstractController {
    private final UserReceiveAddressService userReceiveAddressService;

    @Autowired
    public UserReceiveAddressController(UserReceiveAddressService userReceiveAddressService) {
        this.userReceiveAddressService = userReceiveAddressService;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String add(@ModelAttribute UserReceiveAddress userReceiveAddress) {
        return dataJson(userReceiveAddressService.add(userReceiveAddress), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@ModelAttribute UserReceiveAddress userReceiveAddress, @PathVariable Integer id) {
        return dataJson(userReceiveAddressService.update(id, userReceiveAddress), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        return dataJson(userReceiveAddressService.delete(id), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String get() {
        return dataJson(userReceiveAddressService.get(), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String selectById(@PathVariable Integer id) {
        return dataJson(userReceiveAddressService.selectById(id), Code.SUCCESS.getCode());
    }

}
