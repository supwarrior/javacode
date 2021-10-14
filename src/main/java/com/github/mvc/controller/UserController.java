package com.github.mvc.controller;

import com.github.annotation.Component;
import com.github.annotation.Inject;
import com.github.annotation.RepeatSubmit;
import com.github.mvc.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 康盼Java开发工程师
 */
@Component
@RequestMapping("/api/user")
public class UserController {

    @Inject
    private IUserService userService;

    /**
     * 请求 http://localhost:8028/api/user/getUserName
     * @return 康盼
     */
    @GetMapping(path = "/getUserName")
    @ResponseBody
    @RepeatSubmit
    public String getUserName() {
        return userService.getUserName();
    }

    /**
     * 请求 http://localhost:8028/api/user/getRootUserName
     * @return pan.kang
     */
    @GetMapping(path = "/getRootUserName")
    @ResponseBody
    @RepeatSubmit
    public String getRootUserName() {
        return userService.getRootUserName();
    }

    /**
     * 请求 http://localhost:8028/api/user/getSuperUserName
     * @return 李婷
     */
    @GetMapping(path = "/getSuperUserName")
    @ResponseBody
    @RepeatSubmit
    public String getSuperUserName() {
        return userService.getSuperUserName();
    }
}
