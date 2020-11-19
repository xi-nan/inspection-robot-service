//package com.boot.commons.core.enums;
//
//import com.baomidou.mybatisplus.core.enums.IEnum;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
///**
// * ChooseType
// *
// * @author XINAN
// * @date 2019/7/20
// */
//public enum ChooseTypeEnum implements IEnum<String> {
//    /**
//     * 是
//     */
//    Y,
//    /**
//     * 否
//     */
//    N;
//
//    public static ChooseTypeEnum of(Boolean value) {
//        if (value) {
//            return Y;
//        }
//        else {
//            return N;
//        }
//    }
//
//    @JsonCreator
//    public static ChooseTypeEnum of(String value) {
//        if (StringUtils.isBlank(value) ||
//                !Arrays.stream(values()).map(Enum::name).collect(Collectors.toList()).contains(value)) {
//            return N;
//        }
//        else {
//            return valueOf(value);
//        }
//    }
//
//    @Override
//    public String getValue() {
//        return this.name();
//    }
//
//}
