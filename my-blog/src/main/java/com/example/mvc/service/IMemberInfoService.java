package com.example.mvc.service;

import com.example.mvc.model.MemberInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
public interface IMemberInfoService extends IService<MemberInfo> {

    MemberInfo findMemberById(String id);

}
