package com.etnlgravtnl.modules.dao.menu;

import com.etnlgravtnl.modules.model.menu.SysRoleMenuKey;

public interface SysRoleMenuDao {
    int deleteByPrimaryKey(SysRoleMenuKey key);

    int insert(SysRoleMenuKey record);

    int insertSelective(SysRoleMenuKey record);
}