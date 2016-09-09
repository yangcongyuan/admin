package com.etnlgravtnl.modules.dao.menu;

import com.etnlgravtnl.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface SysMenuDao {
    /**
     * 获取菜单列表
     * @param args
     * @return
     */
    List<Map<String,Object>> getMenu(Map<String,Object> args);
    /**
     * 获取菜单列表数量
     * @param args
     * @return
     */
    int getMenuCount(Map<String,Object> args);
    /**
     * 删除菜单
     * @param args
     */
    void deleteById(Map<String,Object> args);
    /**
     * 修改菜单
     */
    void updateByPrimaryKey(Map<String,Object> args);
    /**
     * 获取菜单信息
     * @param args
     * @return
     */
    Map<String,Object> selectById(Map<String,Object> args);
    /**
     * 插入菜单
     * @param args
     */
    int insert(Map<String,Object> args);
    void insertRole(Map<String,Object> args);
    /**
     * 获取二级菜单
     *
     * @return
     */
    List<Map<String,Object>> getSecondaryMenu();
}