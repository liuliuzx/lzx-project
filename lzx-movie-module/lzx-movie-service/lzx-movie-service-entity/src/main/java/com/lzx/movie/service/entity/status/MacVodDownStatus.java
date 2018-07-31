package com.lzx.movie.service.entity.status;

import com.lzx.admin.base.common.BaseStatus;

/**
 * 系统用户状态类
 * @author Rlax
 *
 */
public class MacVodDownStatus extends BaseStatus {

	public final static String UNUSED = "0";
    public final static String USED = "1";

    public MacVodDownStatus() {
    	add(UNUSED, "停用");
        add(USED, "启用");
    }

    private static MacVodDownStatus me;

    public static MacVodDownStatus me() {
        if (me == null) {
            me = new MacVodDownStatus();
        }
        return me;
    }
}
