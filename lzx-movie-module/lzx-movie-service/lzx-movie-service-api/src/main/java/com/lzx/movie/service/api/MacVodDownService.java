package com.lzx.movie.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.lzx.movie.service.entity.model.MacVodDown;

import java.util.List;

public interface MacVodDownService  {

	/**
     * 分页查询下载组
     * @param macVod
     * @return
     */
    public Page<MacVodDown> findPage(MacVodDown macVodDown, int pageNumber, int pageSize);
    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public MacVodDown findById(Object id);


    /**
     * find all model
     *
     * @return all <MacVodDown
     */
    public List<MacVodDown> findAll();


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
    public boolean delete(MacVodDown model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(MacVodDown model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(MacVodDown model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(MacVodDown model);


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