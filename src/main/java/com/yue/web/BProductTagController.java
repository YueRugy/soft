package com.yue.web;

import com.yue.entity.ProductTag;
import com.yue.enums.Code;
import com.yue.service.ProductTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


/**
 * Created by yue on 2017/9/15
 */
@RestController
@RequestMapping("/back/productTag")
public class BProductTagController extends AbstractController {
    private final ProductTagService productTagService;

    @Autowired
    public BProductTagController(ProductTagService productTagService) {
        this.productTagService = productTagService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String add(@ModelAttribute ProductTag productTag) {
        return dataJson(productTagService.add(productTag), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@ModelAttribute ProductTag productTag, @PathVariable Integer id) {
        return dataJson(productTagService.update(productTag, id), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        return dataJson(productTagService.delete(id), Code.SUCCESS.getCode());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String get(@PageableDefault Pageable pageable) {
        return dataJson(productTagService.get(pageable), Code.SUCCESS.getCode());
    }
}
