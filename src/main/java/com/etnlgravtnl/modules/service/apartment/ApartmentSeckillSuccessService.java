package com.etnlgravtnl.modules.service.apartment;

import com.etnlgravtnl.modules.dao.apartment.SeckillSuccessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/20.
 */
@Transactional
@Service
public class ApartmentSeckillSuccessService {

    @Autowired
    private SeckillSuccessDao seckillResultDao;

    public Map<String,Object> getList(Map<String, Object> args) {

        List<Map<String, Object>> list = seckillResultDao.getListByPage(args);
        int cnt = seckillResultDao.countList(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;

    }

    public void updateStatusByApartId(Map<String, Object> args) {

        seckillResultDao.updateStatusByApartId(args);
    }
}
