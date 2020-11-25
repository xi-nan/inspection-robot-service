package com.boot.business.historicaldata.model.enums;

/**
 * VideoType
 *
 * @author xinan
 * @date 2020/11/9
 */
public enum VideoType {

    /**
     * 可见光
     */
    VISIBLE("可见光"),
    /**
     * 热成像
     */
    THERMOGRAPHY("热成像");


    private final String desc;

    VideoType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
