package com.boot.commons.core.model.po;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * BasePo
 *
 * @author XINAN
 * @date 2019/7/20
 */

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class BasePo<T extends BasePo<T>> extends Model<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    @Column(columnDefinition = "BIGINT(20) COMMENT '记录主键'")
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    @Column(columnDefinition = "VARCHAR(512) DEFAULT '[SYS]' COMMENT '记录创建者'")
    private String creator;

    @TableField(fill = FieldFill.INSERT)
    @Column(columnDefinition = "BIGINT(13) COMMENT '记录创建时间'")
    private Long createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Column(columnDefinition = "VARCHAR(512) DEFAULT '[SYS]' COMMENT '记录修改者'")
    private String modifier;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Column(columnDefinition = "BIGINT(13) COMMENT '记录修改时间'")
    private Long modifyTime;

    /**
     * 0: 正常
     * 1: 已删除
     * ...
     */
    @TableField(select = false)
    @TableLogic
    @Column(columnDefinition = "bit(1) DEFAULT 0 COMMENT '是否已删除 0:正常 1:已删除'")
    private Integer deleted = 0;

    /**
     * mybatis-plus AR模式必须重写次方法指定ID
     */
    @Override
    public Long pkVal() {
        return this.id;
    }

    /**
     * 将本对象转换成其他对象
     */
    public <R> R warpR(Class<R> clazz, String... ignoreProperties) {
        R target = ReflectUtil.newInstance(clazz);
        BeanUtil.copyProperties(this, target, CopyOptions.create().setIgnoreProperties(ignoreProperties).setIgnoreError(true));
        return target;
    }

    /**
     * 将其他对象转换成本对象
     */
    public <R> T warpT(R source) {
        BeanUtil.copyProperties(source, this);
        return (T) this;
    }

    /**
     * 深度克隆
     */
    @Override
    public BasePo<T> clone() {
        return ObjectUtil.clone(this);
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
