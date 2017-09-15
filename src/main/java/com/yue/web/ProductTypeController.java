package com.yue.web;

import com.yue.entity.ProductType;
import com.yue.enums.Code;
import com.yue.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yue on 2017/9/11
 */
@RestController
@RequestMapping("productType")
public class ProductTypeController extends AbstractController {
    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @RequestMapping(value = "back/", method = RequestMethod.POST)
    public String insert(@ModelAttribute ProductType productType) {
        return dataJson(productTypeService.insert(productType), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "back/{pid}", method = RequestMethod.GET)
    public Object select(@PathVariable Integer pid) {
        return dataJson(productTypeService.select(pid), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "back/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id) {
        return dataJson(productTypeService.delete(id), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "back/{id}", method = RequestMethod.PUT)
    public Object put(@PathVariable Integer id, ProductType productType) {
        return dataJson(productTypeService.put(id, productType), Code.SUCCESS.getCode());
    }


}
