package com.cryface.easyblog.controller;


import com.cryface.easyblog.common.lang.Result;
import com.cryface.easyblog.entity.User;
import com.cryface.easyblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cryface
 * @since 2021-05-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public Result index(){
        User byId = userService.getById(1L);
        return Result.succ(200,"查询成功",byId);
    }


}
