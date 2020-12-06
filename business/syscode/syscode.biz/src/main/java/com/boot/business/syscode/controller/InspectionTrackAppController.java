package com.boot.business.syscode.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.boot.business.syscode.model.dto.InspectionTrackDTO;
import com.boot.business.syscode.model.param.InspectionTrackSaveParam;
import com.boot.business.syscode.model.po.InspectionTrack;
import com.boot.commons.core.controller.CURDController;
import com.boot.commons.core.model.param.PageParam;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "客户端-巡检轨迹管理")
@RestController
@RequestMapping(value = "/app/inspectionTrack")
public class InspectionTrackAppController extends CURDController<InspectionTrack, InspectionTrackDTO, InspectionTrackSaveParam> {

    @Override
    public IPage<InspectionTrackDTO> page(PageParam param) {
        return super.page(param);
    }
}
