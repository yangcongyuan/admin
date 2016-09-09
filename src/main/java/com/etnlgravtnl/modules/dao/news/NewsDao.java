package com.etnlgravtnl.modules.dao.news;

import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;
import com.etnlgravtnl.modules.model.News;
import com.etnlgravtnl.modules.model.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/4.
 */
@MyBatisDao
public interface NewsDao {

    List<Map<String,Object>> getAllList(Map<String, Object> args);

    int countList(Map<String, Object> args);

    void save(Map<String, Object> args);

    void update(Map<String, Object> args);

    Map<String,Object> get(Map<String, Object> args);

    void del(Map<String, Object> args);
}
