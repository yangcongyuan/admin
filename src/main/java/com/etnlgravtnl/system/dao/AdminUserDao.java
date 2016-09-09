package com.etnlgravtnl.system.dao;


import com.etnlgravtnl.common.persistence.CrudDao;
import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;
import com.etnlgravtnl.system.entity.AdminUser;
@MyBatisDao
public interface AdminUserDao extends CrudDao<AdminUser>
{

}