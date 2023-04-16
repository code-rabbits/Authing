package com.zut.admin.controller;

import com.zut.admin.entity.SysUser;
import com.zut.admin.security.dto.UserUpdateProfileDTO;
import com.zut.admin.security.dto.UserUpdatePwdDTO;
import com.zut.admin.service.SysUserService;
import com.zut.admin.utils.AliyunOSSUtils;
import com.zut.admin.vo.UserProfileVO;
import com.zut.common.result.AjaxResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.UUID;

/**
 * @Date 2023-04-14 16:40 星期五
 * @Author: 聂建强
 * @Description: 用户-个人详情接口
 */
@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

    @Resource
    private SysUserService userService;

    @Resource
    private AliyunOSSUtils aliyunOSSUtils;


    /**
     * 个人详情
     */
    @RequestMapping("/detail")
    public AjaxResult getUserProfile(Principal principal){

        // 获取当前登录的用户名
        String username = principal.getName();
        //获取登录用户信息
        SysUser loginUser = userService.selectSysUserByUsername(username);
        // 获取用户详情信息
        UserProfileVO userProfile = userService.getUserProfile(loginUser.getUserId());

        return AjaxResult.ok(userProfile);

    }


    /**
     * 修改个人信息
     *
     * @param userUpdateProfileDTO
     */
    @PutMapping("/update")
    public AjaxResult updateProfile(@RequestBody UserUpdateProfileDTO userUpdateProfileDTO) {

        userService.updateProfile(userUpdateProfileDTO);

        return  AjaxResult.ok("修改成功");
    }


    /**
     * 修改个人密码
     *
     * @param userUpdatePwdDTO
     */
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(@RequestBody UserUpdatePwdDTO userUpdatePwdDTO) {

        userService.updatePwd(userUpdatePwdDTO);

        return AjaxResult.ok("修改成功");
    }


    /**
     * 个人-头像上传
     */
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarFile") MultipartFile avatarFile,Principal principal) throws Exception{
        if (!avatarFile.isEmpty()){
            // 获取当前登录用户名
            String username = principal.getName();
            // 获得原始文件名
            String originalFilename = avatarFile.getOriginalFilename();
            // 获取图片类型
            String contentType = avatarFile.getContentType();
            //唯一的文件名称(避免同名文件覆盖问题)
            String filename = UUID.randomUUID().toString() + "." + org.apache.commons.lang3.StringUtils.substringAfterLast(originalFilename, ".");
            // 上传到阿里云OSS
            String avatar = aliyunOSSUtils.uploadImage(filename, avatarFile.getBytes(), contentType, 1000);
            // 上传成功则返回图片地址
            if (userService.updateUserAvatar(username, avatar)) {
                return AjaxResult.ok(avatar);
            }

        }
        return AjaxResult.fail("上传图片异常，请联系管理员");
    }
}
