package com.boot.business.syscode.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.business.syscode.model.dto.InspectionTrackDTO;
import com.boot.business.syscode.model.param.InspectionTrackPageParam;
import com.boot.business.syscode.model.param.InspectionTrackSaveParam;
import com.boot.business.syscode.model.po.InspectionTrack;
import com.boot.commons.core.controller.BaseCURDController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台-巡检轨迹管理")
@RestController
@RequestMapping(value = "/sys/inspectionTrack")
public class InspectionTrackSysController extends BaseCURDController<InspectionTrack, InspectionTrackDTO, InspectionTrackPageParam, InspectionTrackSaveParam> {

    @Override
    protected Wrapper<InspectionTrack> wrapperPageQuery(InspectionTrackPageParam param) {
        return Wrappers.<InspectionTrack>lambdaQuery()
                .eq(param.getEnabled() != null, InspectionTrack::getEnabled, param.getEnabled())
                .like(StrUtil.isNotBlank(param.getTrackName()), InspectionTrack::getTrackName, param.getEnabled())
                .ge(param.getCreateTimeStart() != null, InspectionTrack::getCreateTime, param.getCreateTimeStart())
                .le(param.getCreateTimeEnd() != null, InspectionTrack::getCreateTime, param.getCreateTimeEnd());
    }

}