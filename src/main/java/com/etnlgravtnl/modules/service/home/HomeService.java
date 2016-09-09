package com.etnlgravtnl.modules.service.home;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etnlgravtnl.modules.dao.home.HomeDao;

@Service
@Transactional
public class HomeService 
{
	@Autowired
	private HomeDao homeDao;
	
	public Map<String, Object> queryBanner(Map<String, Object> args)
    {
		List<Map<String, Object>> list = homeDao.queryBanner(args);
		int cnt = homeDao.queryBannerCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addBanner(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		homeDao.addBanner(args);
    }
	
	public Map<String, Object> getBanner(Map<String, Object> args)
	{
		return homeDao.getBanner(args);
	}
	
	public void updBanner(Map<String, Object> args)
    {
		homeDao.updBanner(args);
    }
	
	public void delBanner(Map<String, Object> args)
    {
		homeDao.delBanner(args);
    }
	
	public Map<String, Object> queryIntroduce(Map<String, Object> args)
    {
		List<Map<String, Object>> list = homeDao.queryIntroduce(args);
		int cnt = homeDao.queryIntroduceCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addIntroduce(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		homeDao.addIntroduce(args);
    }
	
	public Map<String, Object> getIntroduce(Map<String, Object> args)
	{
		return homeDao.getIntroduce(args);
	}
	
	public void updIntroduce(Map<String, Object> args)
    {
		homeDao.updIntroduce(args);
    }
	
	public void delIntroduce(Map<String, Object> args)
    {
		homeDao.delIntroduce(args);
    }
	
	public Map<String, Object> queryLayoutRecommend(Map<String, Object> args)
    {
		List<Map<String, Object>> list = homeDao.queryLayoutRecommend(args);
		int cnt = homeDao.queryLayoutRecommendCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addLayoutRecommend(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		homeDao.addLayoutRecommend(args);
    }
	
	public Map<String, Object> getLayoutRecommend(Map<String, Object> args)
	{
		return homeDao.getLayoutRecommend(args);
	}
	
	public void updLayoutRecommend(Map<String, Object> args)
    {
		homeDao.updLayoutRecommend(args);
    }
	
	public void delLayoutRecommend(Map<String, Object> args)
    {
		homeDao.delLayoutRecommend(args);
    }
	
	public Map<String, Object> queryLayoutOverview(Map<String, Object> args)
    {
		List<Map<String, Object>> list = homeDao.queryLayoutOverview(args);
		int cnt = homeDao.queryLayoutOverviewCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addLayoutOverview(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		homeDao.addLayoutOverview(args);
    }
	
	public Map<String, Object> getLayoutOverview(Map<String, Object> args)
	{
		return homeDao.getLayoutOverview(args);
	}
	
	public void updLayoutOverview(Map<String, Object> args)
    {
		homeDao.updLayoutOverview(args);
    }
	
	public void delLayoutOverview(Map<String, Object> args)
    {
		homeDao.delLayoutOverview(args);
    }
	
	public Map<String, Object> queryInformation(Map<String, Object> args)
    {
		List<Map<String, Object>> list = homeDao.queryInformation(args);
		int cnt = homeDao.queryInformationCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addInformation(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		homeDao.addInformation(args);
    }
	
	public Map<String, Object> getInformation(Map<String, Object> args)
	{
		return homeDao.getInformation(args);
	}
	
	public void updInformation(Map<String, Object> args)
    {
		homeDao.updInformation(args);
    }
	
	public void delInformation(Map<String, Object> args)
    {
		homeDao.delInformation(args);
    }
}











