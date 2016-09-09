package com.etnlgravtnl.modules.dao.home;

import java.util.List;
import java.util.Map;

import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface HomeDao 
{
	public List<Map<String, Object>> queryBanner(Map<String, Object> args);

    public int queryBannerCnt(Map<String, Object> args);
    
    public int addBanner(Map<String, Object> args);
    
    public Map<String, Object> getBanner(Map<String, Object> args);
    
    public int updBanner(Map<String, Object> args);
    
    public int delBanner(Map<String, Object> args);
    
    public List<Map<String, Object>> queryIntroduce(Map<String, Object> args);

    public int queryIntroduceCnt(Map<String, Object> args);
    
    public int addIntroduce(Map<String, Object> args);
    
    public Map<String, Object> getIntroduce(Map<String, Object> args);
    
    public int updIntroduce(Map<String, Object> args);
    
    public int delIntroduce(Map<String, Object> args);
    
    public List<Map<String, Object>> queryLayoutRecommend(Map<String, Object> args);

    public int queryLayoutRecommendCnt(Map<String, Object> args);
    
    public int addLayoutRecommend(Map<String, Object> args);
    
    public Map<String, Object> getLayoutRecommend(Map<String, Object> args);
    
    public int updLayoutRecommend(Map<String, Object> args);
    
    public int delLayoutRecommend(Map<String, Object> args);
    
    public List<Map<String, Object>> queryLayoutOverview(Map<String, Object> args);

    public int queryLayoutOverviewCnt(Map<String, Object> args);
    
    public int addLayoutOverview(Map<String, Object> args);
    
    public Map<String, Object> getLayoutOverview(Map<String, Object> args);
    
    public int updLayoutOverview(Map<String, Object> args);
    
    public int delLayoutOverview(Map<String, Object> args);
    
    public List<Map<String, Object>> queryInformation(Map<String, Object> args);

    public int queryInformationCnt(Map<String, Object> args);
    
    public int addInformation(Map<String, Object> args);
    
    public Map<String, Object> getInformation(Map<String, Object> args);
    
    public int updInformation(Map<String, Object> args);
    
    public int delInformation(Map<String, Object> args);
}















