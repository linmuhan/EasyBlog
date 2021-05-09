package com.cryface.easyblog.controller;


import com.cryface.easyblog.common.lang.Result;
import com.cryface.easyblog.entity.User;
import com.cryface.easyblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User byId = userService.getById(1L);
        return Result.succ(200,"查询成功",byId);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.succ(user);
    }


}
