package com.boot.business.syscode.service;

import com.boot.business.syscode.model.dto.SysCodeDTO;
import com.boot.business.syscode.model.enums.ISysCodeEnum;

public interface SysCodeFacade {
    SysCodeDTO get(ISysCodeEnum code);
}
