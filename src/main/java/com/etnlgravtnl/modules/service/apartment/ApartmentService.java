package com.etnlgravtnl.modules.service.apartment;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etnlgravtnl.common.utils.DateUtils;
import com.etnlgravtnl.modules.dao.apartment.ApartmentDao;

@Service
@Transactional
public class ApartmentService 
{
	@Autowired
	private ApartmentDao apartmentDao;
	
	public Map<String, Object> queryCommunity(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryCommunity(args);
		int cnt = apartmentDao.queryCommunityCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addCommunity(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		apartmentDao.addCommunity(args);
    }
	
	public Map<String, Object> getCommunity(Map<String, Object> args)
	{
		return apartmentDao.getCommunity(args);
	}
	
	public void updCommunity(Map<String, Object> args)
    {
		apartmentDao.updCommunity(args);
    }
	
	public void delCommunity(Map<String, Object> args)
    {
		apartmentDao.delCommunity(args);
    }
	
	public List<Map<String, Object>> queryCommunitySel()
	{
		return apartmentDao.queryCommunitySel();
	}
	
	public Map<String, Object> queryApartmentType(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryApartmentType(args);
		int cnt = apartmentDao.queryApartmentTypeCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addApartmentType(Map<String, Object> args)
    {
		apartmentDao.addApartmentType(args);
    }
	
	public Map<String, Object> getApartmentType(Map<String, Object> args)
	{
		return apartmentDao.getApartmentType(args);
	}
	
	public void updApartmentType(Map<String, Object> args)
    {
		apartmentDao.updApartmentType(args);
    }
	
	public void delApartmentType(Map<String, Object> args)
    {
		apartmentDao.delApartmentType(args);
    }
	
	public Map<String, Object> queryApartmentArea(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryApartmentArea(args);
		int cnt = apartmentDao.queryApartmentAreaCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addApartmentArea(Map<String, Object> args)
    {
		apartmentDao.addApartmentArea(args);
    }
	
	public Map<String, Object> getApartmentArea(Map<String, Object> args)
	{
		return apartmentDao.getApartmentArea(args);
	}
	
	public void updApartmentArea(Map<String, Object> args)
    {
		apartmentDao.updApartmentArea(args);
    }
	
	public void delApartmentArea(Map<String, Object> args)
    {
		apartmentDao.delApartmentArea(args);
    }
	
	public Map<String, Object> queryApartmentHouseType(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryApartmentHouseType(args);
		int cnt = apartmentDao.queryApartmentHouseTypeCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addApartmentHouseType(Map<String, Object> args)
    {
		apartmentDao.addApartmentHouseType(args);
    }
	
	public Map<String, Object> getApartmentHouseType(Map<String, Object> args)
	{
		return apartmentDao.getApartmentHouseType(args);
	}
	
	public void updApartmentHouseType(Map<String, Object> args)
    {
		apartmentDao.updApartmentHouseType(args);
    }
	
	public void delApartmentHouseType(Map<String, Object> args)
    {
		apartmentDao.delApartmentHouseType(args);
    }
	
	public List<Map<String, Object>> queryApartmentTypeSel()
	{
		return apartmentDao.queryApartmentTypeSel();
	}
	
	public List<Map<String, Object>> queryApartmentAreaSel()
	{
		return apartmentDao.queryApartmentAreaSel();
	}
	
	public List<Map<String, Object>> queryApartmentHouseTypeSel()
	{
		return apartmentDao.queryApartmentHouseTypeSel();
	}
	
	public List<Map<String, Object>> queryModelSel()
	{
		return apartmentDao.queryModelSel();
	}
	
	public List<Map<String, Object>> queryLayoutSel()
	{
		return apartmentDao.queryLayoutSel();
	}
	
	public List<Map<String, Object>> queryEnvironmentSel()
	{
		return apartmentDao.queryEnvironmentSel();
	}
	
	public Map<String, Object> queryApartment(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryApartment(args);
		int cnt = apartmentDao.queryApartmentCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addApartment(Map<String, Object> args) throws ParseException 
    {
		String net_day = (String) args.get("net_day");
		Date net_dayD = DateUtils.parseDate(net_day);
		Date net_day7D = DateUtils.getDateCompute(net_dayD, -7);
		String net_day7 = DateUtils.parseDate(net_day7D);
		args.put("net_day7", net_day7);
		args.put("create_time", new Date());
		apartmentDao.addApartment(args);
    }
	
	public Map<String, Object> getApartment(Map<String, Object> args)
	{
		return apartmentDao.getApartment(args);
	}
	
	public void updApartment(Map<String, Object> args) throws ParseException
    {
		String net_day = (String) args.get("net_day");
		Date net_dayD = DateUtils.parseDate(net_day);
		Date net_day7D = DateUtils.getDateCompute(net_dayD, -7);
		String net_day7 = DateUtils.parseDate(net_day7D);
		args.put("net_day7", net_day7);
		apartmentDao.updApartment(args);
    }
	
	public void delApartment(Map<String, Object> args)
    {
		apartmentDao.delApartment(args);
    }
	
	public Map<String, Object> queryModel(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryModel(args);
		int cnt = apartmentDao.queryModelCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addModel(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		apartmentDao.addModel(args);
    }
	
	public Map<String, Object> getModel(Map<String, Object> args)
	{
		return apartmentDao.getModel(args);
	}
	
	public void updModel(Map<String, Object> args)
    {
		apartmentDao.updModel(args);
    }
	
	public void delModel(Map<String, Object> args)
    {
		apartmentDao.delModel(args);
    }
	
	public Map<String, Object> queryLayout(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryLayout(args);
		int cnt = apartmentDao.queryLayoutCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addLayout(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		apartmentDao.addLayout(args);
    }
	
	public Map<String, Object> getLayout(Map<String, Object> args)
	{
		return apartmentDao.getLayout(args);
	}
	
	public void updLayout(Map<String, Object> args)
    {
		apartmentDao.updLayout(args);
    }
	
	public void delLayout(Map<String, Object> args)
    {
		apartmentDao.delLayout(args);
    }
	
	public Map<String, Object> queryEnvironment(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryEnvironment(args);
		int cnt = apartmentDao.queryEnvironmentCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addEnvironment(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		apartmentDao.addEnvironment(args);
    }
	
	public Map<String, Object> getEnvironment(Map<String, Object> args)
	{
		return apartmentDao.getEnvironment(args);
	}
	
	public void updEnvironment(Map<String, Object> args)
    {
		apartmentDao.updEnvironment(args);
    }
	
	public void delEnvironment(Map<String, Object> args)
    {
		apartmentDao.delEnvironment(args);
    }
	
	public Map<String, Object> queryHousePrice(Map<String, Object> args)
    {
		List<Map<String, Object>> list = apartmentDao.queryHousePrice(args);
		int cnt = apartmentDao.queryHousePriceCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addHousePrice(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		apartmentDao.addHousePrice(args);
    }
	
	public Map<String, Object> getHousePrice(Map<String, Object> args)
	{
		return apartmentDao.getHousePrice(args);
	}
	
	public void updHousePrice(Map<String, Object> args)
    {
		apartmentDao.updHousePrice(args);
    }
	
	public void delHousePrice(Map<String, Object> args)
    {
		apartmentDao.delHousePrice(args);
    }
}










