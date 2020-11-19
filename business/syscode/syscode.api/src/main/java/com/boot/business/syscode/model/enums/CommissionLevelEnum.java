package com.boot.business.syscode.model.enums;

public enum CommissionLevelEnum implements ISysCodeEnum {

    L_1("一级费率"),

    l_2("二级费率");

    private final String text;

    CommissionLevelEnum(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
