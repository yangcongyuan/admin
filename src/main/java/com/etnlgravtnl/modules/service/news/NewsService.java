package com.etnlgravtnl.modules.service.news;

import com.etnlgravtnl.modules.dao.news.NewsDao;
import com.etnlgravtnl.modules.model.News;
import com.etnlgravtnl.modules.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/4.
 */
@Service("newsService")
public class NewsService {

    @Autowired
    private NewsDao newDao;

    public Map<String, Object> getAllList(Map<String, Object> args)
    {
        List<Map<String, Object>> list = newDao.getAllList(args);
        int cnt = newDao.countList(args);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }

    public void save(Map<String, Object> args) {
        Integer publish = Integer.parseInt(args.get("publish").toString());
        if(publish == 1){
            args.put("publishDate",new Date());
        }
        newDao.save(args);
    }

    public void update(Map<String, Object> args) {
        Integer publish = Integer.parseInt(args.get("publish").toString());
        if(publish == 1){
            args.put("publishDate",new Date());
        }
        newDao.update(args);
    }


    public Map<String,Object> get(Map<String, Object> args) {

        return newDao.get(args);
    }

    public void del(Map<String, Object> args) {

        newDao.del(args);
    }
}
