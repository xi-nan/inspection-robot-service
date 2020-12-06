package com.boot.business.syscode.model.po;

import com.boot.commons.core.model.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author XINAN
 */

@EqualsAndHashCode(callSuper = true)
@Data

@Entity
@Table(appliesTo = "sys_code", comment = "字典", indexes = {
        @Index(name = "IX_SysCode_deleted", columnNames = {"deleted"}),
        @Index(name = "IX_SysCode_deleted_code", columnNames = {"deleted", "code"}),
        @Index(name = "IX_SysCode_deleted_parentCode", columnNames = {"deleted", "parentCode"})
})
public class SysCode extends BasePo<SysCode> {

    @Column(columnDefinition = "varchar(255) COMMENT 'code,不可重复'")
    private String code;

    @Column(columnDefinition = "text COMMENT '名称'")
    private String name;

    @Column(columnDefinition = "text COMMENT '内容'")
    private String content;

    @Column(columnDefinition = "varchar(255) COMMENT '父code'")
    private String parentCode;

    @Column(columnDefinition = "BIGINT(4) COMMENT '排序'")
    private Long sort;

    @Column(columnDefinition = "bit(1) COMMENT '是否启用 0:false 1:true'")
    private Boolean enabled = true;

    public SysCode(String code, String name, String content, String parentCode, Long sort, Boolean enabled) {
        this.code = code;
        this.name = name;
        this.content = content;
        this.parentCode = parentCode;
        this.sort = sort;
        this.enabled = enabled;
    }

    public SysCode() {
    }
}
