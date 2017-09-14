package com.yue.service.impl;

import com.yue.entity.Clue;
import com.yue.enums.ClueDeal;
import com.yue.enums.ClueShow;
import com.yue.enums.ClueStatus;
import com.yue.mapper.ClueMapper;
import com.yue.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yue on 2017/9/14
 */
@Service
@Transactional
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;

    @Override
    public Object add(Clue clue) {

        clue.setRecommendUserId(1);
        clue.setIsDeal(ClueDeal.notDeal.getValue());
        clue.setIsShow(ClueShow.show.getValue());
        clue.setStatus(ClueStatus.waitDistribution.getValue());

        return clueMapper.insert(clue);
    }

    @Override
    public Object getByCommId(Integer id, Pageable pageable) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("pageable", pageable);
        return clueMapper.selectClueByCommIdByPage(param);

    }
}
