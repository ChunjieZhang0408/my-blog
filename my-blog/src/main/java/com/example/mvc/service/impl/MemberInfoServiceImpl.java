package com.example.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.constant.Constants;
import com.example.mvc.model.MemberInfo;
import com.example.mvc.mapper.MemberInfoDao;
import com.example.mvc.service.IMemberInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2020-01-12
 */
@Service
public class MemberInfoServiceImpl extends ServiceImpl<MemberInfoDao, MemberInfo> implements IMemberInfoService {

    @Override
    public MemberInfo findMemberById(String id) {
        QueryWrapper<MemberInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id).eq("status", Constants.NORMAL);
        MemberInfo memberInfo = this.getOne(queryWrapper);
        return memberInfo;
    }
}
