package com.boot.business.syscode.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.appuser.service.AppUserFacade;
import com.boot.business.syscode.mapper.EquipmentMapper;
import com.boot.business.syscode.model.dto.EquipmentDTO;
import com.boot.business.syscode.model.po.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EquipmentService
 *
 * @author XINAN
 */
@Service
public class EquipmentService extends ServiceImpl<EquipmentMapper, Equipment> {

    @Autowired
    private AppUserFacade userFacade;

    public EquipmentDTO getById(Long id) {
        return this.warpDTO(super.getById(id));
    }

    public EquipmentDTO warpDTO(Equipment po) {
        if (null == po) {
            return null;
        }
        EquipmentDTO dto = po.warpR(EquipmentDTO.class);
        dto.setUsername(userFacade.getDtoById(po.getUserId()).getUsername());
        return dto;
    }
}
