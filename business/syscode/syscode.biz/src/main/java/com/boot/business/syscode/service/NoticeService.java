package com.boot.business.syscode.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.business.syscode.mapper.NoticeMapper;
import com.boot.business.syscode.model.po.Notice;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * SysCodeService
 *
 * @author XINAN
 * @date 2020/6/12
 */
@Service
@CacheConfig(cacheNames = "Notice")
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {


}
