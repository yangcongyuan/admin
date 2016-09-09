package com.etnlgravtnl.common.service;

import com.etnlgravtnl.common.persistence.DataEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface CrudService<T extends DataEntity> {
    /**
     * 获取单条数据
     * @param id
     * @return
     */
     public T get(String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) ;

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * 保存数据（插入或更新）
     * @param entity
     */

    public void save(T entity);
    /**
     * 保存数据（插入或更新）
     * @param entity
     */

    public int saveInt(T entity);
    /**
     * 删除数据
     * @param entity
     */
    public void delete(T entity);


}
