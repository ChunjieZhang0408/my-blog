package com.example.mvc.service.impl;

import com.example.mvc.model.SysNotice;
import com.example.mvc.mapper.SysNoticeDao;
import com.example.mvc.service.ISysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统公告表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeDao, SysNotice> implements ISysNoticeService {

}
