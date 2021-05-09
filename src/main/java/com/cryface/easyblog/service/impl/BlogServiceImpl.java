package com.cryface.easyblog.service.impl;

import com.cryface.easyblog.entity.Blog;
import com.cryface.easyblog.mapper.BlogMapper;
import com.cryface.easyblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
