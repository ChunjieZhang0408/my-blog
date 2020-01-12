package com.example;

import com.example.mvc.mapper.SysRoleDao;
import com.example.mvc.model.SysRole;
import com.example.mvc.service.ISysRoleService;
import com.example.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Desc test
 * @Author ZhangChunjie
 * @Date 2020/1/12 11:18
 * @Version 1.0
 */
@SpringBootTest
public class BlogTest {

    @Autowired
    private ISysRoleService sysRoleService;

    @Test
    public void saveRole(){
        SysRole sysRole = new SysRole();
        sysRole.setName("管理员");
        sysRole.setDescription("admin");
        sysRole.setCreateTime(DateUtils.nowTime());
        sysRoleService.save(sysRole);
    }
}
