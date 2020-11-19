package com.boot.business.syscode.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.syscode.mapper.EquipmentMapper;
import com.boot.business.syscode.model.dto.EquipmentDTO;
import com.boot.business.syscode.model.po.Equipment;
import org.springframework.stereotype.Service;

/**
 * EquipmentService
 *
 * @author XINAN
 */
@Service
public class EquipmentService extends ServiceImpl<EquipmentMapper, Equipment> {

    public EquipmentDTO getById(Long id) {
        return this.warpDTO(super.getById(id));
    }

    public EquipmentDTO warpDTO(Equipment po) {
        if (null == po) {
            return null;
        }
        EquipmentDTO dto = po.warpR(EquipmentDTO.class);
        return dto;
    }
}
