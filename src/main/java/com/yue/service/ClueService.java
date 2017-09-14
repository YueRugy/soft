package com.yue.service;

import com.yue.entity.Clue;
import org.springframework.data.domain.Pageable;


/**
 * Created by yue on 2017/9/14
 */

public interface ClueService {
    Object add(Clue clue);

    Object getByCommId(Integer id, Pageable pageable);

    Object detail(Integer id);

    Object delete(Integer id);
}
