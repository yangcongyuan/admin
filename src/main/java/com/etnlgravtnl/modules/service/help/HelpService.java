package com.etnlgravtnl.modules.service.help;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etnlgravtnl.modules.dao.help.HelpDao;

@Service
@Transactional
public class HelpService 
{
	@Autowired
	private HelpDao helpDao;
	
	public Map<String, Object> queryQuestionType(Map<String, Object> args)
    {
		List<Map<String, Object>> list = helpDao.queryQuestionType(args);
		int cnt = helpDao.queryQuestionTypeCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addQuestionType(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		helpDao.addQuestionType(args);
    }
	
	public Map<String, Object> getQuestionType(Map<String, Object> args)
	{
		return helpDao.getQuestionType(args);
	}
	
	public void updQuestionType(Map<String, Object> args)
    {
		helpDao.updQuestionType(args);
    }
	
	public void delQuestionType(Map<String, Object> args)
    {
		helpDao.delQuestionType(args);
    }
	
	public List<Map<String, Object>> queryQuestionTypeSel()
	{
		return helpDao.queryQuestionTypeSel();
	}
	
	public Map<String, Object> queryQuestion(Map<String, Object> args)
    {
		List<Map<String, Object>> list = helpDao.queryQuestion(args);
		int cnt = helpDao.queryQuestionCnt(args);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
        result.put("recordsTotal", cnt);
        result.put("recordsFiltered", cnt);
        result.put("draw", args.get("draw"));
        return result;
    }
	
	public void addQuestion(Map<String, Object> args)
    {
		args.put("create_time", new Date());
		helpDao.addQuestion(args);
    }
	
	public Map<String, Object> getQuestion(Map<String, Object> args)
	{
		return helpDao.getQuestion(args);
	}
	
	public void updQuestion(Map<String, Object> args)
    {
		helpDao.updQuestion(args);
    }
	
	public void delQuestion(Map<String, Object> args)
    {
		helpDao.delQuestion(args);
    }
}









