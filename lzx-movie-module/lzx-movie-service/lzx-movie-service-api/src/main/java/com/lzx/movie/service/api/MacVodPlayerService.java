package com.lzx.movie.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lzx.movie.service.entity.model.MacVod;
import com.lzx.movie.service.entity.model.MacVodPlayer;

import java.util.List;

public interface MacVodPlayerService  {

	/**
     * 分页查询播放器组
     * @param macVod
     * @return
     */
    public Page<MacVodPlayer> findPage(MacVodPlayer macVodPlayer, int pageNumber, int pageSize);
    
    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public MacVodPlayer findById(Object id);


    /**
     * find all model
     *
     * @return all <MacVodPlayer
     */
    public List<MacVodPlayer> findAll();


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
    public boolean delete(MacVodPlayer model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(MacVodPlayer model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(MacVodPlayer model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(MacVodPlayer model);


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