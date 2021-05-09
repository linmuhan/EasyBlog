package com.cryface.easyblog.service.impl;

import com.cryface.easyblog.entity.User;
import com.cryface.easyblog.mapper.UserMapper;
import com.cryface.easyblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cryface
 * @since 2021-05-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
