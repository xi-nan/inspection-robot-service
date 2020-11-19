package com.boot.business.syscode.model.enums;

public enum SysCodeEnum implements ISysCodeEnum {

    /**
     * 直播
     */
    LIVES("直播"),
    /**
     * 平台横幅
     */
    BANNERS("平台横幅"),

    /**
     * 问题库
     */
    QUESTIONS("问题库"),

    /**
     * 关于我们
     */
    ABOUT_US("关于我们"),
    /**
     * 玩法介绍
     */
    HOW_TO_PLAY("玩法介绍"),
    /**
     * 专业洗护
     */
    PROFESSIONAL_CARE("专业洗护"),
    /**
     * 用户须知
     */
    NOTICE_TO_USERS("用户须知"),

    /**
     * 用户协议
     */
    USER_AGREEMENT("用户协议"),
    /**
     * 服务协议
     */
    SERVICE_AGREEMENT("服务协议"),
    /**
     * 每10积分可兑换金额
     */
    INTEGRAL_EXCHANGE("每10积分可兑换金额");


    private final String text;

    SysCodeEnum(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
