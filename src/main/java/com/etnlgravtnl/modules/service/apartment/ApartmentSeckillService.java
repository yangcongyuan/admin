package com.etnlgravtnl.modules.service.apartment;

import com.etnlgravtnl.modules.dao.apartment.ApartmentDao;
import com.etnlgravtnl.modules.dao.apartment.ApartmentSeckillDao;
import com.etnlgravtnl.modules.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/19.
 */
@Service
@Transactional
public class ApartmentSeckillService {

    @Autowired
    private ApartmentSeckillDao apartmentSeckillDao;

    @Autowired
    private ApartmentDao apartmentDao;

    public Map<String,Object> getList(Map<String, Object> args) {
        List<Map<String, Object>> list = apartmentSeckillDao.getListByPage(args);
        int cnt = apartmentSeckillDao.countList(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }

    public Map<String,Object> get(Map<String, Object> args) {

        return apartmentSeckillDao.get(args);
    }

    public void add(Map<String, Object> args) {
        Integer user_id = Integer.parseInt((String) args.get("user_id"));
        if(user_id != 0){
            apartmentDao.updateApartmentUserIdById(args);
        }
        apartmentSeckillDao.add(args);
    }

    public void edit(Map<String, Object> args) {
        Integer user_id = Integer.parseInt((String) args.get("user_id"));
        if(user_id != 0){
            apartmentDao.updateApartmentUserIdById(args);
        }
        apartmentSeckillDao.edit(args);
    }

    public void editStatusUp(Map<String, Object> args) {
        args.put("apar_status","2");
        apartmentDao.updateAparStatusIdById(args);
    }

    public void editStatusDown(Map<String, Object> args) {
        args.put("apar_status","0");
        apartmentDao.updateAparStatusIdById(args);
    }
}
