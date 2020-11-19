package com.boot.commons.core.constants;

/**
 * PatternConstants
 *
 * @author XINAN
 * @date 2019/7/22
 */
public class PatternConstants {

    /**
     * 密码(以字母开头，长度在5~18之间，只能包含字母、数字和下划线)
     */
    public static final String LOGIN_NAME = "^[a-zA-Z][a-zA-Z0-9_]{4,17}$";

    /**
     * 密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
     */
    public static final String PASSWORD = "^[a-zA-Z][a-zA-Z0-9_]{5,17}$";

    /**
     * 强密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在6-18之间)
     */
    public static final String STRONG_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,17}$";

    /**
     * 英文字母 、数字和下划线
     */
    public static final String GENERAL = "^\\w+$";

    /**
     * 数字
     */
    public static final String NUMBERS = "\\d+";

    /**
     * 字母
     */
    public static final String WORD = "[a-zA-Z]+";

    /**
     * 单个中文汉字
     */
    public static final String CHINESE = "[\u4E00-\u9FFF]";

    /**
     * 中文汉字
     */
    public static final String CHINESES = "$CHINESE+";

    /**
     * 分组
     */
    public static final String GROUP_VAR = "\\$(\\d+,";

    /**
     * IP v4
     */
    public static final String IPV4 = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";

    /**
     * IP v6
     */
    public static final String IPV6 = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";

    /**
     * 货币
     */
    public static final String MONEY = "^(\\d+(?:\\.\\d+)?)$";

    /**
     * 邮件，使用时需配置忽略大小写<Pattern.compile(PatternConstants.EMAIL, Pattern.CASE_INSENSITIVE)>
     * 符合RFC 5322规范，正则来自：http://emailregex.com/
     */
    public static final String EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    /**
     * 移动电话
     */
    public static final String MOBILE = "(?:0|86|\\+86)?1[3456789]\\d{9}";

    /**
     * 18位身份证号码
     */
    public static final String CITIZEN_ID = "[1-9]\\d{5}[1-2]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|X|x)";

    /**
     * 邮编
     */
    public static final String ZIP_CODE = "[1-9]\\d{5}(?!\\d)";

    /**
     * 生日
     */
    public static final String BIRTHDAY = "^(\\d{2,4})([/\\-\\.年]?)(\\d{1,2})([/\\-\\.月]?)(\\d{1,2})日?$";

    /**
     * URL
     */
    public static final String URL = "[a-zA-z]+://[^\\s]*";

    /**
     * Http URL
     */
    public static final String URL_HTTP = "(https://|http://)?([\\w-]+\\.)+[\\w-]+(:\\d+)*(/[\\w- ./?%&=]*)?";

    /**
     * 中文字、英文字母、数字和下划线
     */
    public static final String GENERAL_WITH_CHINESE = "^[\u4E00-\u9FFF\\w]+$";

    /**
     * UUID
     */
    public static final String UUID = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$";

    /**
     * 不带横线的UUID
     */
    public static final String UUID_SIMPLE = "^[0-9a-z]{32}$";

    /**
     * 中国车牌号码
     */
    public static final String PLATE_NUMBER = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";

    /**
     * MAC地址正则，使用时需配置忽略大小写<Pattern.compile(PatternConstants.MAC_ADDRESS, Pattern.CASE_INSENSITIVE)>
     */
    public static final String MAC_ADDRESS = "((?:[A-F0-9]{1,2}[:-]){5}[A-F0-9]{1,2})|(?:0x)(\\d{12})(?:.+ETHER)";

    /**
     * 16进制字符串，使用时需配置忽略大小写<Pattern.compile(PatternConstants.HEX, Pattern.CASE_INSENSITIVE)>
     */
    public static final String HEX = "^[a-f0-9]+$";

    /**
     * 时间正则
     */
    public static final String TIME = "\\d{1,2}:\\d{1,2}(:\\d{1,2})?";
}
