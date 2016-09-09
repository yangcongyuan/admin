package com.etnlgravtnl.modules.dao.apartment;

import java.util.List;
import java.util.Map;

import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface ApartmentDao 
{
	public List<Map<String, Object>> queryCommunity(Map<String, Object> args);

    public int queryCommunityCnt(Map<String, Object> args);
    
    public int addCommunity(Map<String, Object> args);
    
    public Map<String, Object> getCommunity(Map<String, Object> args);
    
    public int updCommunity(Map<String, Object> args);
    
    public int delCommunity(Map<String, Object> args);
    
    public List<Map<String, Object>> queryCommunitySel();
    
    public List<Map<String, Object>> queryApartmentType(Map<String, Object> args);

    public int queryApartmentTypeCnt(Map<String, Object> args);
    
    public int addApartmentType(Map<String, Object> args);
    
    public Map<String, Object> getApartmentType(Map<String, Object> args);
    
    public int updApartmentType(Map<String, Object> args);
    
    public int delApartmentType(Map<String, Object> args);
    
    public List<Map<String, Object>> queryApartmentArea(Map<String, Object> args);

    public int queryApartmentAreaCnt(Map<String, Object> args);
    
    public int addApartmentArea(Map<String, Object> args);
    
    public Map<String, Object> getApartmentArea(Map<String, Object> args);
    
    public int updApartmentArea(Map<String, Object> args);
    
    public int delApartmentArea(Map<String, Object> args);
    
    public List<Map<String, Object>> queryApartmentHouseType(Map<String, Object> args);

    public int queryApartmentHouseTypeCnt(Map<String, Object> args);
    
    public int addApartmentHouseType(Map<String, Object> args);
    
    public Map<String, Object> getApartmentHouseType(Map<String, Object> args);
    
    public int updApartmentHouseType(Map<String, Object> args);
    
    public int delApartmentHouseType(Map<String, Object> args);
    
    public List<Map<String, Object>> queryApartmentTypeSel();
    
    public List<Map<String, Object>> queryApartmentAreaSel();
    
    public List<Map<String, Object>> queryApartmentHouseTypeSel();
    
    public List<Map<String, Object>> queryModelSel();
    
    public List<Map<String, Object>> queryLayoutSel();
    
    public List<Map<String, Object>> queryEnvironmentSel();
    
    public List<Map<String, Object>> queryApartment(Map<String, Object> args);

    public int queryApartmentCnt(Map<String, Object> args);
    
    public int addApartment(Map<String, Object> args);
    
    public Map<String, Object> getApartment(Map<String, Object> args);
    
    public int updApartment(Map<String, Object> args);
    
    public int delApartment(Map<String, Object> args);
    
    public List<Map<String, Object>> queryModel(Map<String, Object> args);

    public int queryModelCnt(Map<String, Object> args);
    
    public int addModel(Map<String, Object> args);
    
    public Map<String, Object> getModel(Map<String, Object> args);
    
    public int updModel(Map<String, Object> args);
    
    public int delModel(Map<String, Object> args);
    
    public List<Map<String, Object>> queryLayout(Map<String, Object> args);

    public int queryLayoutCnt(Map<String, Object> args);
    
    public int addLayout(Map<String, Object> args);
    
    public Map<String, Object> getLayout(Map<String, Object> args);
    
    public int updLayout(Map<String, Object> args);
    
    public int delLayout(Map<String, Object> args);
    
    public List<Map<String, Object>> queryEnvironment(Map<String, Object> args);

    public int queryEnvironmentCnt(Map<String, Object> args);
    
    public int addEnvironment(Map<String, Object> args);
    
    public Map<String, Object> getEnvironment(Map<String, Object> args);
    
    public int updEnvironment(Map<String, Object> args);
    
    public int delEnvironment(Map<String, Object> args);
    
    public List<Map<String, Object>> queryHousePrice(Map<String, Object> args);

    public int queryHousePriceCnt(Map<String, Object> args);
    
    public int addHousePrice(Map<String, Object> args);
    
    public Map<String, Object> getHousePrice(Map<String, Object> args);
    
    public int updHousePrice(Map<String, Object> args);
    
    public int delHousePrice(Map<String, Object> args);

    int updateApartmentUserIdById(Map<String, Object> args);

    int updateAparStatusIdById(Map<String, Object> args);
}














