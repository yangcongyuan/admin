package com.etnlgravtnl.system.service;

import com.etnlgravtnl.common.service.CrudService;
import com.etnlgravtnl.system.entity.Menu;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface MenuService extends CrudService<Menu>{
    public List<Menu> generateMenuTreeList(List<Menu> menuList4User);

    public String getMenuHtmlText4User(String userId);
    public List<Menu> getMenu4User(String userId);




}
