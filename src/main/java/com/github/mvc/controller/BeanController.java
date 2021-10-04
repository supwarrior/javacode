package com.github.mvc.controller;

import com.github.annotation.Component;
import com.github.annotation.RepeatSubmit;
import com.github.javabean.Beans;
import com.github.mvc.model.User;
import com.github.mvc.service.IBeanService;
import com.github.mvc.service.IUserService;
import com.github.mvc.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 康盼Java开发工程师
 */
@Component
@RequestMapping("/api/bean")
public class BeanController {
    
    @Autowired
    private IBeanService beanService;


    /**
     * 请求 http://localhost:8028/api/bean/loadSpringFactoriesBean
     *
     * @return 康盼
     */
    @GetMapping(path = "/loadSpringFactoriesBean")
    @ResponseBody
    @RepeatSubmit
    public int loadSpringFactoriesBean() throws Exception {
        IBeanService beanService = (IBeanService) Beans.getByName("beanService");
        beanService.loadSpringFactoriesBean();
        return -1;
    }

    @GetMapping(path = "/initializeAndDestroy/{beanName}")
    @ResponseBody
    @RepeatSubmit
    public int initializeAndDestroy(@PathVariable(name = "beanName") String beanName) {
        User user = (User) Beans.getByName("user");
        IUserService userService = (UserService) Beans.getByName("userService");
        beanService.initBean(beanName);
        beanService.destroyBean();
        return -1;
    }
}
