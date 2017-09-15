package com.yue.web;

import com.yue.enums.Code;
import com.yue.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yue on 2017/9/15
 */
@RestController
@RequestMapping("area")
public class AreaController extends AbstractController {
    private final AreaService areaService;

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public String selectByPid(@PathVariable Integer pid) {
        return dataJson(areaService.selectByPid(pid), Code.SUCCESS.getCode());
    }

}
