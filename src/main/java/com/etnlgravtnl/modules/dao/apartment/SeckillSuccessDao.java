package com.etnlgravtnl.modules.dao.apartment;

import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface SeckillSuccessDao {

    List<Map<String,Object>> getListByPage(Map<String, Object> args);

    int countList(Map<String, Object> args);

    void updateStatusByApartId(Map<String, Object> args);
}