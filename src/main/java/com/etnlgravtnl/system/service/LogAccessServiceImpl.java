package com.etnlgravtnl.system.service;

import com.etnlgravtnl.common.service.impl.BaseServiceImpl;
import com.etnlgravtnl.system.dao.LogAccessDao;
import com.etnlgravtnl.system.entity.LogAccess;
import com.etnlgravtnl.system.entity.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2016/6/20.
 */
@Service(value="logAccessService")
@Transactional
public class LogAccessServiceImpl extends BaseServiceImpl {

    @Autowired
    private LogAccessDao logAccessDao;

    public SearchResult findPageList(LogAccess logAccess) {

        SearchResult<LogAccess> result = new SearchResult<>();
        result.setTotalRows(logAccessDao.coutPageList(logAccess));
        result.setRows(logAccessDao.findPageList(logAccess));

        return result ;
    }

}
