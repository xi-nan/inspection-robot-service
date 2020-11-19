package com.boot.business.syscode.model.enums;

public enum IntegralTypeEnum implements ISysCodeEnum {

    CHECK_IN("签到"),

    EVALUATION("晒单"),

//    Share("分享"),

    PAY("每支付1元可得积分");

    private final String text;

    IntegralTypeEnum(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
