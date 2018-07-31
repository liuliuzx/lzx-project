package com.lzx.movie.service.api;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.lzx.movie.service.entity.model.MacVod;

import java.util.List;

public interface MacVodService  {
	
	
	/**
     * 分页查询电影资源
     * @param macVod
     * @return
     */
    public Page<Record> findPage(MacVod macVod, int pageNumber, int pageSize);
    
    /**
     * 查询某个电影的资源列表
     * @param macVod
     * @return
     */
    public List<Record> findVodResById(Long id);
    
    /**保存电影资源信息
     * 
     * @param macVod
     * @return
     */
    public boolean saveMacVod(MacVod macVod);
    
    public boolean updateMacVod(MacVod macVod);
    
 

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public MacVod findById(Object id);


    /**
     * find all model
     *
     * @return all <MacVod
     */
    public List<MacVod> findAll();


    /**
     * delete model by primary key
     *
     * @param id
     * @return success
     */
    public boolean deleteById(Object id);


    /**
     * delete model
     *
     * @param model
     * @return
     */
    public boolean delete(MacVod model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(MacVod model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(MacVod model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(MacVod model);


    public void join(Page<? extends Model> page, String joinOnField);
    public void join(Page<? extends Model> page, String joinOnField, String[] attrs);
    public void join(Page<? extends Model> page, String joinOnField, String joinName);
    public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs);
    public void join(List<? extends Model> models, String joinOnField);
    public void join(List<? extends Model> models, String joinOnField, String[] attrs);
    public void join(List<? extends Model> models, String joinOnField, String joinName);
    public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs);
    public void join(Model model, String joinOnField);
    public void join(Model model, String joinOnField, String[] attrs);
    public void join(Model model, String joinOnField, String joinName);
    public void join(Model model, String joinOnField, String joinName, String[] attrs);

    public void keep(Model model, String... attrs);
    public void keep(List<? extends Model> models, String... attrs);

}