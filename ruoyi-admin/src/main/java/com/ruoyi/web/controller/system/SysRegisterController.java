package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.framework.web.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册
 * @author dfm
 * @create 2021-04-09 16:13
 */
@Api("用户注册")
@RestController
@RequestMapping("/system/register")
public class SysRegisterController {

    @Autowired
    RegisterService registerService;

    @ApiOperation("注册")
    @PostMapping
    public AjaxResult register(@RequestBody RegisterBody registerUser){
        AjaxResult register = registerService.register(registerUser);
        return register;
    }
}
