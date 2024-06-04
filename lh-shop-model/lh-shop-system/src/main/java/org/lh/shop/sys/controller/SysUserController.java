package org.lh.shop.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lh.shop.common.core.domin.R;
import org.lh.shop.mybatis.page.PageQuery;
import org.lh.shop.sys.dto.SysUserDto;
import org.lh.shop.sys.service.ISysUserService;
import org.lh.shop.system.api.domain.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuYf
 * @date 2024/6/4 13:35
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;

    @GetMapping("/page")
    public R<IPage<SysUser>> pageUser(SysUserDto userDto, PageQuery pageQuery) {
        return R.ok(sysUserService.pageUser(userDto, pageQuery));
    }

    @GetMapping("/list")
    public R<List<SysUser>> listUser(SysUserDto userDto) {
        return R.ok(sysUserService.listUser(userDto));
    }
}
