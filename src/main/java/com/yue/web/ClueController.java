package com.yue.web;

import com.yue.entity.Clue;
import com.yue.enums.Code;
import com.yue.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


/**
 * Created by yue on 2017/9/14
 */
@RestController
@RequestMapping("clue")
public class ClueController extends AbstractController {
    private final ClueService clueService;

    @Autowired
    public ClueController(ClueService clueService) {
        this.clueService = clueService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object add(@ModelAttribute Clue clue) {
        return dataJson(clueService.add(clue), Code.SUCCESS.getCode());
    }

    @RequestMapping("/{id}")
    public String get(@PathVariable Integer id, @PageableDefault(size = 15, sort = {"create_time"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return dataJson(clueService.getByCommId(id, pageable), Code.SUCCESS.getCode());
    }

}
