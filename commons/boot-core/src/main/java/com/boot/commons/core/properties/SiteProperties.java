package com.boot.commons.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * SiteProperties
 *
 * @author XINAN
 * @date 2019/7/22
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "site")
public class SiteProperties {

    public static SiteProperties _this;
    /**
     * 终端ID (0~31)
     */
    private Long workerId = 0L;
    /**
     * 数据中心ID (0~31)
     */
    private Long dataCenterId = 0L;
    /**
     * 项目名
     */
    private String appName = "Project";
    /**
     * 项目中文名
     */
    private String explanation = null;
    /**
     * 接口域名
     */
    private String host = "http://localhost/";
    /**
     * 接口域名
     */
    private String h5Host = "http://localhost/";
    /**
     * 版本
     */
    private String version = "1.0";
    private String tag = null;
    /**
     * 本地文件上传保存目录
     */
    private String fileUploadDir = "/data/" + appName + "/file/";
    /**
     * 本地文件下载域名
     */
    private String fileDownloadDoMain = "";

    @PostConstruct
    public void init() {
        _this = this;
    }

    public void setHost(String host) {
        this.host = host.endsWith("/") ? host : host + "/";
    }

    public void setH5Host(String h5Host) {
        this.h5Host = h5Host.endsWith("/") ? h5Host : h5Host + "/";
    }

    public void setFileDownloadDoMain(String fileDownloadDoMain) {
        this.fileDownloadDoMain = fileDownloadDoMain + (!fileDownloadDoMain.endsWith("/") ? "/" : "");
    }

    public void setFileUploadDir(String fileUploadDir) {
        this.fileUploadDir = fileUploadDir + (!fileUploadDir.endsWith("/") ? "/" : "");
    }

}