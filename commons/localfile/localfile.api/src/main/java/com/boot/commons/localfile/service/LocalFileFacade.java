package com.boot.commons.localfile.service;

import com.boot.commons.localfile.model.dto.LocalFileDTO;

import java.io.File;
import java.util.function.Function;

/**
 * LocalFileFacade
 *
 * @author xinan
 * @date 2020/11/26
 */
public interface LocalFileFacade {

    File loadFile(Long fileId);

    LocalFileDTO findById(Long fileId);

    void recodeVideo(Long fileId, Function<Long, Boolean> func);

}
