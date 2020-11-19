package com.boot.business.syscode.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.syscode.mapper.SysCodeMapper;
import com.boot.business.syscode.model.dto.SysCodeDTO;
import com.boot.business.syscode.model.enums.ISysCodeEnum;
import com.boot.business.syscode.model.po.SysCode;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.properties.SiteProperties;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * SysCodeService
 *
 * @author XINAN
 * @date 2020/6/12
 */
@Service
@CacheConfig(cacheNames = "SysCode")
public class SysCodeService extends ServiceImpl<SysCodeMapper, SysCode> implements SysCodeFacade {

    public void save(String code, SysCode sysCode) {
        if (code != null) {
            ErrCodeEnum.E_10021.throwIf(!this.lambdaUpdate()
                    .eq(SysCode::getCode, code)
                    .set(SysCode::getName, sysCode.getName())
                    .set(SysCode::getContent, sysCode.getContent())
                    .set(SysCode::getSort, sysCode.getSort())
                    .update());
        } else {
            sysCode.setCode(IdUtil.getSnowflake(SiteProperties._this.getWorkerId(), SiteProperties._this.getDataCenterId()).nextIdStr());
            ErrCodeEnum.E_10021.throwIf(!this.save(sysCode));
        }
    }

    @CacheEvict(key = "#code.name()")
    public boolean save(ISysCodeEnum code, String content) {
        SysCode sysCode = new SysCode();
        sysCode.setCode(code.name());
        sysCode.setName(code.getText());
        sysCode.setContent(content);
        SysCode one = super.lambdaQuery().eq(SysCode::getCode, code.name()).one();
        if (null != one) {
            sysCode.setId(one.getId());
        }
        ErrCodeEnum.E_10021.throwIf(!super.saveOrUpdate(sysCode));
        return true;
    }

    @Override
    @Cacheable(key = "#code.name()")
    public SysCodeDTO get(ISysCodeEnum code) {
        SysCode one = super.lambdaQuery().eq(SysCode::getCode, code.name()).last(" limit 1 ").one();
        SysCodeDTO dto = new SysCodeDTO();
        dto.setCode(code.name());
        dto.setName(code.getText());
        if (null != one) {
            dto.setContent(one.getContent());
        }
        return dto;
    }
}
