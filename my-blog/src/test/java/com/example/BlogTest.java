package com.example;

import com.example.mvc.model.ArticleType;
import com.example.mvc.model.MemberInfo;
import com.example.mvc.model.SysRole;
import com.example.mvc.service.IArticleTypeService;
import com.example.mvc.service.IMemberInfoService;
import com.example.mvc.service.ISysRoleService;
import com.example.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

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

    @Autowired
    private IMemberInfoService memberInfoService;

    @Autowired
    private IArticleTypeService articleTypeService;

    @Test
    public void saveRole() {
        SysRole sysRole = new SysRole();
        sysRole.setName("管理员");
        sysRole.setDescription("admin");
        sysRole.setCreateTime(DateUtils.nowTime());
        sysRoleService.save(sysRole);
    }

    @Test
    public void saveArticleType() {
        ArticleType articleType;
        String[] names = {"Java", "Python", "Linux", "Html", "Vue", "数据库", "Spring", "Spring boot", "Spring cloud", "mybatis"};
        ArrayList<ArticleType> typeArrayList = new ArrayList<>();
        for (String name : names) {
            articleType = new ArticleType();
            articleType.setName(name);
            articleType.setStatus(1);
            articleType.setCreateTime(DateUtils.nowTime());
            articleType.setDescription(name);
            typeArrayList.add(articleType);
        }
        articleTypeService.saveBatch(typeArrayList);
    }
}
