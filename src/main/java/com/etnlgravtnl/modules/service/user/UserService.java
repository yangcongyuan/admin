package com.etnlgravtnl.modules.service.user;

import com.etnlgravtnl.modules.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService
{
    @Autowired
    private UserDao userDao;

    public Map<String,Object> getAllList(Map<String, Object> args) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = userDao.getAllList(args);
        result.put("data",list);
        return result;
    }
}
