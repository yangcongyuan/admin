package com.etnlgravtnl.modules.dao.apartment;

import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/19.
 */
@MyBatisDao
public interface ApartmentSeckillDao {

    List<Map<String,Object>> getListByPage(Map<String, Object> args);

    int countList(Map<String, Object> args);

    Map<String,Object> get(Map<String, Object> args);

    void add(Map<String, Object> args);

    void edit(Map<String, Object> args);
}
