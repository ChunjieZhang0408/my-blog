package com.example.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mvc.model.MemberInfo;
import com.example.mvc.model.SysResources;
import com.example.mvc.model.SysRoleResources;
import com.example.mvc.service.IMemberInfoService;
import com.example.mvc.service.ISysResourcesService;
import com.example.mvc.service.ISysRoleResourcesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc CustomUserDetailsService
 * @Author ZhangChunjie
 * @Date 2020/1/12 12:01
 * @Version 1.0
 */

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IMemberInfoService memberInfoService;

    @Autowired
    private ISysRoleResourcesService roleResourcesService;

    @Autowired
    private ISysResourcesService resourcesService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        QueryWrapper<MemberInfo> memberInfoQueryWrapper = new QueryWrapper<>();
        memberInfoQueryWrapper.eq("account", account);
        MemberInfo memberInfo = memberInfoService.getOne(memberInfoQueryWrapper);
        if (memberInfo == null) {
            throw new UsernameNotFoundException(String.format("%s不存在", account));
        }
        String roleId = memberInfo.getRoleId();
        QueryWrapper<SysRoleResources> roleResourcesQueryWrapper = new QueryWrapper<>();
        roleResourcesQueryWrapper.eq("role_id", roleId);
        List<SysRoleResources> roleResourcesList = roleResourcesService.list(roleResourcesQueryWrapper);
        ArrayList<String> resourceIdList = new ArrayList<>();
        roleResourcesList.forEach(e -> {
            resourceIdList.add(e.getResourcesId());
        });
        List<SysResources> sysResources = resourcesService.listByIds(resourceIdList);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority;

        for (SysResources e : sysResources) {
            grantedAuthority = new SimpleGrantedAuthority(e.getUrl());
            grantedAuthorities.add(grantedAuthority);
        }
        CustomUserDetails userDetails = new CustomUserDetails();
        BeanUtils.copyProperties(memberInfo,userDetails);
        userDetails.setAuthorities(grantedAuthorities);
        return userDetails;
    }
}
