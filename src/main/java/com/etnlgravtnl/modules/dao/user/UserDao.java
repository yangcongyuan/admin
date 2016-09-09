package com.etnlgravtnl.modules.dao.user;

import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface UserDao
{
	List<Map<String, Object>> getAllList(Map<String, Object> args);


}








