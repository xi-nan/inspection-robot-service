package com.boot.business.historicaldata.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SimpImportListener<T> extends AnalysisEventListener {
    //存放临时数据
    private List<T> datas = new ArrayList<>();

    public List<T> getDatas() {
        return datas;
    }

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
//        InspectionBatteryDTO dto = (InspectionBatteryDTO) o;
        datas.add((T) o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
