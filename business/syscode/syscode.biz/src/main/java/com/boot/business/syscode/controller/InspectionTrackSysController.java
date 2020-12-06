package com.boot.business.syscode.controller;

import com.boot.business.syscode.model.dto.InspectionTrackDTO;
import com.boot.business.syscode.model.po.InspectionTrack;
import com.boot.commons.core.controller.ReadController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "后台-巡检轨迹管理")
@RestController
@RequestMapping(value = "/sys/inspectionTrack")
public class InspectionTrackSysController extends ReadController<InspectionTrack, InspectionTrackDTO> {

}
