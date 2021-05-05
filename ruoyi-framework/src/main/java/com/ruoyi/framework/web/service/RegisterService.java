package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dfm
 * @create 2021-04-09 16:21
 */
@Component
public class RegisterService {

    @Autowired
    ISysUserService  userService;

    @Autowired
    RedisCache redisCache;


    public AjaxResult register(RegisterBody  registerUser){
        String uuid = registerUser.getUuid();
        String code = registerUser.getCode();

        //验证码验证
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }


        //注册用户检验
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(registerUser.getUsername())))
        {
            return AjaxResult.error("新增用户'" + registerUser.getUsername() + "'失败，登录账号已存在");
        }
//        else if (StringUtils.isNotEmpty(user.getPhonenumber())
//                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
//        {
//            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
//        }
//        else if (StringUtils.isNotEmpty(user.getEmail())
//                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
//        {
//            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
//        }
        SysUser user = new SysUser();
        user.setNickName(registerUser.getNickName());
        user.setUserName(registerUser.getUsername());
        user.setCreateBy(user.getUserName());//自己注册
        user.setPassword(SecurityUtils.encryptPassword(registerUser.getPassword()));
        user.setRoleIds(new Long[]{2l});//添加角色为普通角色
        user.setDeptId(110L);// 普通部门 获取普通角色数据
        int result = userService.insertUser(user);
        if(result > 0){
            return  AjaxResult.success("注册成功");
        }else return AjaxResult.error("注册失败");
    }
}
