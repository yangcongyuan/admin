package com.etnlgravtnl.system.dao;

import com.etnlgravtnl.common.persistence.CrudDao;
import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;
import com.etnlgravtnl.system.entity.LogAccess;

import java.util.List;

@MyBatisDao
public interface LogAccessDao extends CrudDao<LogAccess> {

    long coutPageList(LogAccess logAccess);

    List<LogAccess> findPageList(LogAccess logAccess);
}