package com.boot.commons.localfile.model.enums;

import com.boot.commons.core.exception.enums.IErrCodeEnum;

/**
 * LocalFileErrCodeEnum
 *
 * @author XINAN
 */
public enum LocalFileErrCodeEnum implements IErrCodeEnum {

    E_29500(29500, ""),
    E_29501(29501, ""),
    E_29502(29502, "."),
    E_29503(29503, "文件读取失败请联系开发人员进行处理.{}"),
    E_29504(29504, "文件未找到."),
    E_29505(29505, "文件已存在服务器,无需重复上传."),
    E_29506(29506, "分片上传父记录未找到,请联系开发人员进行处理."),
    E_29507(29507, "分片编号不符合预期值,请联系开发人员进行处理.{}"),
    E_29508(29508, "分片文件大小不符合预期值,请联系开发人员进行处理.{}"),
    E_29509(29509, "分片文件未全部上传无法进行合并"),
    E_29510(29510, "文件合并失败，请重试!{}");

    private final Integer code;
    private final String desc;

    LocalFileErrCodeEnum(int code, String desc) {
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