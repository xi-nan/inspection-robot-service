package com.boot.business.syscode.model.po;

import com.boot.business.syscode.model.enums.NoticeEnum;
import com.boot.commons.core.model.po.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(appliesTo = "notice", comment = "通知消息", indexes = {
        @Index(name = "IX_Notice_deleted", columnNames = {"deleted"})
})
public class Notice extends BasePo<Notice> {

    @ApiModelProperty(value = "外部业务Id")
    @Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT '外部业务Id'")
    private Long outId;

    @ApiModelProperty(value = "通知人")
    @Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT '通知人'")
    private Long appUserId;

    @ApiModelProperty(value = "消息标题")
    @Column(columnDefinition = "VARCHAR(64) NOT NULL COMMENT '消息标题'")
    private String title;

    @ApiModelProperty(value = "消息内容")
    @Column(columnDefinition = "VARCHAR(512) NOT NULL COMMENT '消息内容'")
    private String content;

    @ApiModelProperty(value = "消息类型")
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'NONE' COMMENT '消息类型'")
    private NoticeEnum noticeEnum;
}
