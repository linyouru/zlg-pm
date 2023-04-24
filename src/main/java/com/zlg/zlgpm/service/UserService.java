package com.zlg.zlgpm.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlg.zlgpm.commom.OperationLog;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.UserRoleMapper;
import com.zlg.zlgpm.pojo.bo.UserListBo;
import com.zlg.zlgpm.pojo.bo.UserMessageBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.pojo.po.UserRolePo;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.helper.DataConvertHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends ServiceImpl<UserMapper, UserPo> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;

    @Transactional(rollbackFor = Exception.class)
    @OperationLog(value = "创建用户", type = "User")
    public ApiUserLoginResponse createUser(ApiCreateUserRequest apiCreateUserRequest) {
        UserPo userPo = dataConvertHelper.convert2UserPo(apiCreateUserRequest);
        userPo.setStatus(1);
        Long count = userMapper.selectCount(new QueryWrapper<UserPo>().eq("username", userPo.getUserName()));
        if (count > 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10001");
        }
        userMapper.insert(userPo);
        UserPo insertUserPo = userMapper.selectOne(new QueryWrapper<UserPo>().eq("username", userPo.getUserName()));
        //添加默认角色
        userRoleMapper.insert(new UserRolePo(insertUserPo.getId(), 3L));
        return dataConvertHelper.convert2ApiUserLoginResponse(insertUserPo);
    }

    @OperationLog(value = "修改用户", type = "User")
    public ApiUserLoginResponse updateUser(Integer id, ApiUpdateUserRequest body) {
        Long count = userMapper.selectCount(new QueryWrapper<UserPo>().eq("id", id));
        if (count < 1) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        }
        UserPo userPo = dataConvertHelper.convert2UserPo(body);
        userPo.setId(id.longValue());
        userMapper.updateById(userPo);
        UserPo retUser = userMapper.selectById(id);
        return dataConvertHelper.convert2ApiUserLoginResponse(retUser);
    }

    public void updatePassword(Integer id, String newPassword, String oldPassword) {
        UserPo select = userMapper.selectById(id);
        if (null == select) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        } else if (!select.getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10003");
        }
        UserPo userPo = new UserPo();
        userPo.setId(Long.valueOf(id));
        userPo.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        userMapper.updateById(userPo);
    }

    public ApiUserListResponse userList(String userName, Integer currentPage, Integer pageSize, String startTime, String endTime) {
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.ne("id", 1);
        if (null != userName) {
            wrapper.likeRight("userName", userName);
        }
        Page<UserListBo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(currentPage);
        //mybatis-plus对于复杂sql中有多处where的情况下好像无法使用多个条件构造器构建sql,需使用xml动态构建sql
        Page<UserListBo> userListBoPage = userMapper.queryUserList(page, wrapper, startTime, endTime);
        return dataConvertHelper.convert2ApiUserListResponse(userListBoPage);
    }

    public ApiUserListByPidResponse userListByPid(Integer pid, String projectName, Integer currentPage, Integer pageSize) {
        ArrayList<Integer> pidList = new ArrayList<>();
        if (StringUtils.isNotEmpty(projectName)) {
            QueryWrapper<ProjectPo> wrapper = new QueryWrapper<>();
            wrapper.eq("name", projectName);
            List<ProjectPo> projectPos = projectMapper.selectList(wrapper);
            for (ProjectPo projectPo : projectPos) {
                pidList.add(projectPo.getId());
            }
        }
        pidList.add(pid);
        Integer[] array = pidList.toArray(new Integer[0]);
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.ne("id", 1);
        wrapper.in("up.pid", array);
        Page<UserListBo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(currentPage);
        Page<UserListBo> userPoPage = userMapper.queryUserListByPid(page, wrapper);
        return dataConvertHelper.convert2ApiUserListByPidResponse(userPoPage);
    }

    public ApiUserListByPidResponse userListByProjectName(String projectName, Integer currentPage, Integer pageSize) {
        QueryWrapper<UserPo> wrapper = new QueryWrapper<>();
        wrapper.eq("p.name", projectName);
        Page<UserListBo> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(currentPage);
        Page<UserListBo> userListBoPage = userMapper.queryUserListByProjectName(page, wrapper);
        return dataConvertHelper.convert2ApiUserListByPidResponse(userListBoPage);
    }

    public ApiUserLoginResponse queryUserByName(String userName) {
        UserPo userPo = userMapper.selectOne(new QueryWrapper<UserPo>().eq("userName", userName));
        return dataConvertHelper.convert2ApiUserLoginResponse(userPo);
    }

    @Transactional(rollbackFor = Exception.class)
    @OperationLog(value = "删除用户", type = "User")
    public ApiUserLoginResponse deleteUser(Integer id) {
        UserPo userPo = userMapper.selectById(id);
        if (null == userPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002", id);
        }
        //删除用户前须先删除用户下挂载的任务,若用户为项目负责人,还要先删除项目
        Long taskCount = taskMapper.selectCount(new QueryWrapper<TaskPo>().eq("uid", id));
        if (taskCount > 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10004", id);
        }
        Long projectCount = projectMapper.selectCount(new QueryWrapper<ProjectPo>().eq("uid", id));
        if (projectCount > 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10005", id);
        }
        userMapper.delete(new QueryWrapper<UserPo>().eq("id", id));
        userRoleMapper.delete(new QueryWrapper<UserRolePo>().eq("uid", id));
        return dataConvertHelper.convert2ApiUserLoginResponse(userPo);
    }

    public ArrayList<Map<String, Object>> getUserInfo() {
        QueryWrapper<Map<String, Object>> wrapper = new QueryWrapper<>();
        wrapper.notIn("userName", "叶玉琳", "root", "admin");
        return userMapper.getUserInfo(wrapper);
    }

    public List<ApiUserMessageResponse> getUserMessage() {
        List<UserMessageBo> userMessage = userMapper.getUserMessage();
        List<ApiUserMessageResponse> responses = dataConvertHelper.convert2ApiUserMessageResponse(userMessage);
        return responses;
    }
}
