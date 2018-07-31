package com.lzx.movie.service.entity.status;

import com.lzx.admin.base.common.BaseStatus;

/**
 * 系统用户状态类
 * @author Rlax
 *
 */
public class MacTypeStatus extends BaseStatus {

    public final static String MOVIE = "1";
    public final static String ARTICLE = "2";

    public MacTypeStatus() {
        add(MOVIE, "电影");
        add(ARTICLE, "文章");
    }

    private static MacTypeStatus me;

    public static MacTypeStatus me() {
        if (me == null) {
            me = new MacTypeStatus();
        }
        return me;
    }
}
