package com.boot.commons.excel;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ListStringConverter
 *
 * @author XINAN
 * @date 2020/1/6
 * @description
 */
public class ListStringConverter<T> implements Converter<List<T>> {

    @Override
    public Class supportJavaTypeKey() {
        return ListStringConverter.class;
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
    public List<T> convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        return JSONUtil.parseArray(cellData.getStringValue())
                .stream().map(it -> BeanUtil.toBean(it, getTClass())).collect(Collectors.toList());

    }

    private Class<T> getTClass() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        return (Class<T>) trueType;
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
    public CellData<String> convertToExcelData(List<T> value, ExcelContentProperty contentProperty,
                                               GlobalConfiguration globalConfiguration) {
        return new CellData<>(JSONUtil.toJsonStr(value));
    }
}
