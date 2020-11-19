package com.boot.commons.core.exception.enums;

/**
 * ErrCodeEnum
 *
 * @author XINAN
 * @date 2019/7/22
 */
public enum ErrCodeEnum implements IErrCodeEnum {
    /**
     * 系统错误
     */
    E_10001(10001, "系统错误.{}"),
    E_10002(10002, "服务暂停.{}"),
    E_10003(10003, "远程服务错误.{}"),
    E_10010(10010, "SQL执行异常.{}"),
    E_10011(10011, "持久化异常(IBatis异常).{}"),
    E_10012(10012, "MybatisPlus异常.{}"),
    E_10013(10013, "配置文件错误.{}"),

    E_10020(10020, "加锁失败.{}"),
    E_10021(10021, "数据保存失败.{}"),
    E_10022(10022, "数据删除失败.{}"),

    E_20001(20001, "不支持的MediaType.{}"),
    E_20002(20002, "RequestBody无法解析,请传入正确的JSON格式“字符串”"),
    E_20003(20003, "缺失必填参数[{}:{}],请参考API文档"),
    E_20004(20004, "参数值非法,请参考API文档.{}"),
    E_20005(20005, "不支持[{}]方式进行请求, 请检查文档使用正确的HTTP METHOD请求"),

    E_20010(20010, "接口访问权限受限.{}"),
    E_20011(20011, "token已失效,请重新登陆"),
    E_20012(20012, "未找到用户名为[{}]的账号"),
    E_20013(20013, "用户名或密码错误"),
    E_20014(20014, "IP限制不能请求该资源"),
    E_20015(20015, "IP请求频次超过上限"),
    E_20016(20016, "用户请求特殊接口[{}]频次超过上限"),

    E_20030(20030, "数据不存在.{}");

    private final Integer code;
    private final String desc;

    ErrCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }


}