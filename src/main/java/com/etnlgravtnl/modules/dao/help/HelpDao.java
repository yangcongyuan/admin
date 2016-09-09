package com.etnlgravtnl.modules.dao.help;

import java.util.List;
import java.util.Map;

import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface HelpDao 
{
	public List<Map<String, Object>> queryQuestionType(Map<String, Object> args);

    public int queryQuestionTypeCnt(Map<String, Object> args);
    
    public int addQuestionType(Map<String, Object> args);
    
    public Map<String, Object> getQuestionType(Map<String, Object> args);
    
    public int updQuestionType(Map<String, Object> args);
    
    public int delQuestionType(Map<String, Object> args);
    
    public List<Map<String, Object>> queryQuestionTypeSel();
    
    public List<Map<String, Object>> queryQuestion(Map<String, Object> args);

    public int queryQuestionCnt(Map<String, Object> args);
    
    public int addQuestion(Map<String, Object> args);
    
    public Map<String, Object> getQuestion(Map<String, Object> args);
    
    public int updQuestion(Map<String, Object> args);
    
    public int delQuestion(Map<String, Object> args);
}










