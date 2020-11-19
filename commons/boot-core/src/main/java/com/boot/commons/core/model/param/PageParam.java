package com.boot.commons.core.model.param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;

/**
 * PageParam
 *
 * @author XINAN
 * @date 2019/8/3
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {

    @ApiModelProperty(value = "第几页，默认第一页", example = "1")
    @Min(value = 1, message = "页码不能小于1")
    private Long current = 1L;

    @ApiModelProperty(value = "每页大小，默认10", example = "10")
    @Min(value = 1, message = "每页大小不能小于1")
    private Long size = 10L;

    @ApiModelProperty(value = "排序信息，排序的字段和正反序", example = "[{\"column\":\"id\", \"asc\":false}]")
    private List<OrderItem> orders = Collections.singletonList(OrderItem.desc("id"));

    public <T> IPage<T> page() {
        Page<T> page = new Page<>(this.current, this.size);
        page.setOrders((this.orders));
        return page;
    }

    public PageParam page(PageParam page) {
        this.current = page.current;
        this.size = page.size;
        this.orders = page.orders;
        return this;
    }


}