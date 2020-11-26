package com.boot.business.syscode.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.business.syscode.model.dto.QuestionDTO;
import com.boot.business.syscode.model.enums.SysCodeEnum;
import com.boot.business.syscode.model.param.QuestionPageParam;
import com.boot.business.syscode.model.po.SysCode;
import com.boot.business.syscode.service.SysCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.*;

@Api(tags = "客户端-系统手册")
@RestController
@RequestMapping(value = "/app/questions")
@CacheConfig(cacheNames = "SysCode")
public class QuestionAppController {

    @Autowired
    private SysCodeService service;

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

    @ApiOperation("获取问题详情")
    @GetMapping("/code/{code}")
    public QuestionDTO one(@PathVariable String code) {
        return QuestionDTO.warp(service.lambdaQuery()
                .eq(SysCode::getParentCode, SysCodeEnum.QUESTIONS.name())
                .eq(SysCode::getCode, code).one()
        );
    }
}
