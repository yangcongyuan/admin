package com.etnlgravtnl.system.dao;


import com.etnlgravtnl.common.persistence.CrudDao;
import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;
import com.etnlgravtnl.system.entity.AdminUser;
import com.etnlgravtnl.system.entity.Menu;

import java.util.List;

@MyBatisDao
public interface MenuDao extends CrudDao<Menu>
{
    public List<Menu> selectMenu4User(String userId);

}