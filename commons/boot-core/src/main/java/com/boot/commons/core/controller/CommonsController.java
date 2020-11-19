package com.boot.commons.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "通用接口")
@RestController
@RequestMapping(value = "/")
public class CommonsController {

    @ApiOperation("获取服务器当前时间")
    @GetMapping("/time/public")
    public Long time() {
        return System.currentTimeMillis();
    }

}
