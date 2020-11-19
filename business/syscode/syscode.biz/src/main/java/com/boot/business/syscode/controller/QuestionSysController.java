package com.boot.business.syscode.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.business.syscode.model.dto.QuestionDTO;
import com.boot.business.syscode.model.enums.SysCodeEnum;
import com.boot.business.syscode.model.param.QuestionPageParam;
import com.boot.business.syscode.model.param.QuestionSaveParam;
import com.boot.business.syscode.model.po.SysCode;
import com.boot.business.syscode.service.SysCodeService;
import com.boot.commons.core.exception.enums.ErrCodeEnum;
import com.boot.commons.core.model.param.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台-系统手册")
@RestController
@RequestMapping(value = "/sys/questions")
@CacheConfig(cacheNames = "SysCode")
public class QuestionSysController {

    @Autowired
    private SysCodeService service;

    @ApiOperation("添加问题")
    @CacheEvict(key = "'questions'")
    @PostMapping("/add")
    public void add(@RequestBody QuestionSaveParam param) {
        service.save(null, param.warp());
    }

    @ApiOperation("修改问题")
    @CacheEvict(key = "'questions'")
    @PostMapping("/edit")
    public void upd(@RequestBody @Validated(value = ValidGroup.upd.class) QuestionSaveParam param) {
        service.save(param.getCode(), param.warp());
    }

    @ApiOperation("删除问题")
    @CacheEvict(key = "'questions'")
    @DeleteMapping("/{code}")
    public void delByIds(@PathVariable String code) {
        ErrCodeEnum.E_10022.throwIf(!service.lambdaUpdate().eq(SysCode::getCode, code).remove());
    }

    @ApiOperation("获取问题列表(分页)")
    @PostMapping("/page")
    public IPage<QuestionDTO> page(@RequestBody QuestionPageParam param) {
        boolean notBlank = StrUtil.isNotBlank(param.getSearchKey());
        return service.lambdaQuery()
                .eq(SysCode::getParentCode, SysCodeEnum.QUESTIONS.name())
                .and(notBlank, query -> query
                        .like(SysCode::getName, param.getSearchKey())
                        .or()
                        .like(SysCode::getContent, param.getSearchKey()))
                .orderByAsc(SysCode::getSort)
                .page(param.page()).convert(QuestionDTO::warp);
    }
}
