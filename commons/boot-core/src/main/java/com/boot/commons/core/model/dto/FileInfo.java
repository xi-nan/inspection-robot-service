package com.boot.commons.core.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XINAN
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    @ApiModelProperty(value = "key")
    private String key;

    @ApiModelProperty(value = "url")
    private String url;


}