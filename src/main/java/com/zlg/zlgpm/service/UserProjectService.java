package com.zlg.zlgpm.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlg.zlgpm.dao.UserProjectMapper;
import com.zlg.zlgpm.pojo.po.UserProjectPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserProjectService extends ServiceImpl<UserProjectMapper, UserProjectPo> implements IService<UserProjectPo> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserProjectMapper userProjectMapper;

    /**
     * 删除项目全部用户关系
     * @param pid
     */
    public void deleteProjectUser(int pid) {
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);
        userProjectMapper.deleteProjectUser(wrapper);
    }

}
