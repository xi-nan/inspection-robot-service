package com.boot.commons.excel;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.Date;

/**
 * CustomLongDateConverter
 * Long类型时间戳和String时间转换
 *
 * @author XINAN
 * @date 2020/1/2
 * @description
 */
public class LongDateConverter implements Converter<Long> {
    @Override
    public Class<Long> supportJavaTypeKey() {
        return Long.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 导入转换
     * 读的时候会调用
     *
     * @param cellData            NotNull
     * @param contentProperty     Nullable
     * @param globalConfiguration NotNull
     * @return
     */
    @Override
    public Long convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) {
        return DateUtil.parse(cellData.getStringValue()).getTime();
    }

    /**
     * 导出转换
     * 写的时候会调用
     *
     * @param value               NotNull
     * @param contentProperty     Nullable
     * @param globalConfiguration NotNull
     * @return
     */
    @Override
    public CellData<String> convertToExcelData(Long value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) {
        return new CellData<>(DateUtil.format(new Date(value), DatePattern.NORM_DATETIME_MS_PATTERN));
    }
}
