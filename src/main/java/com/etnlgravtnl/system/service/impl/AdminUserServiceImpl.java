package com.etnlgravtnl.system.service.impl;

import com.etnlgravtnl.common.service.impl.CrudServiceImpl;
import com.etnlgravtnl.system.dao.AdminUserDao;
import com.etnlgravtnl.system.dao.MessageTemplatesDao;
import com.etnlgravtnl.system.entity.AdminUser;
import com.etnlgravtnl.system.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/29.
 */
@Service("adminUserService")
public class AdminUserServiceImpl extends CrudServiceImpl<AdminUserDao,AdminUser> implements AdminUserService{
    @Autowired
    private AdminUserDao adminUserDao;

}
