package com.etnlgravtnl.modules.service.menu;

import com.etnlgravtnl.modules.dao.menu.SysMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/8/30.
 */
@Service
@Transactional
public class SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    public Map<String,Object> getMenu(Map<String,Object> args){
        List<Map<String,Object>> list=sysMenuDao.getMenu(args);
        int cnt=sysMenuDao.getMenuCount(args);
        Map<String,Object> result=new HashMap<String, Object>();
        result.put("data",list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered",cnt);
        result.put("draw", args.get("draw"));
        return result;
    }


    public void deleteById(Map<String,Object> args){
        sysMenuDao.deleteById(args);
    }
    public void updateByPrimaryKey(Map<String,Object> args){
        args.put("update_date",new Date());
        args.put("update_by","admin");
        sysMenuDao.updateByPrimaryKey(args);
    }
    public Map<String,Object> selectById(Map<String,Object> args){
        return sysMenuDao.selectById(args);
    }

    public void insert(Map<String,Object> args){
        args.put("create_by","dba");
        args.put("update_by","admin");
        args.put("create_date",new Date());
        args.put("update_date",new Date());
        sysMenuDao.insert(args);
        /*args.put("menu_id",args.get("id"));
        sysMenuDao.insertRole(args);*/
    }

    public List<Map<String,Object>> getSecondaryMenu(){
        return sysMenuDao.getSecondaryMenu();
    }
}
