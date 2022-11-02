package com.zlg.zlgpm.helper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.pojo.ProjectBo;
import com.zlg.zlgpm.pojo.ProjectPo;
import com.zlg.zlgpm.pojo.UserPo;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.stream.Collectors;

/**
 * 类型转换工具
 *
 * @author linyouru
 */
@Component
public class DataConvertHelper {


    public UserPo convert2User(ApiCreateUserRequest apiCreateUserRequest) {
        final UserPo userPo = new UserPo();
        String password = apiCreateUserRequest.getPassword();
        userPo.setUserName(apiCreateUserRequest.getUserName());
        userPo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userPo.setNickName(apiCreateUserRequest.getNickName());
        userPo.setEmail(apiCreateUserRequest.getEmail());
        userPo.setRemark(apiCreateUserRequest.getRemark());
        return userPo;
    }

    public UserPo convert2User(ApiUpdateUserRequest apiUpdateUserRequest) {
        UserPo userPo = new UserPo();
        String password = apiUpdateUserRequest.getPassword();
        if (null != password) {
            userPo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        }
        userPo.setNickName(apiUpdateUserRequest.getNickName());
        userPo.setEmail(apiUpdateUserRequest.getEmail());
        userPo.setRemark(apiUpdateUserRequest.getRemark());
        return userPo;
    }

    public ApiUserResponse convert2UserResponse(UserPo userPo) {
        ApiUserResponse apiUserResponse = new ApiUserResponse();
        apiUserResponse.setId(Math.toIntExact(userPo.getId()));
        apiUserResponse.setUserName(userPo.getUserName());
        apiUserResponse.setNickName(userPo.getNickName());
        apiUserResponse.setEmail(userPo.getEmail());
        apiUserResponse.setRemark(userPo.getRemark());
        apiUserResponse.setCreateTime(userPo.getCreateTime());
        apiUserResponse.setUpdateTime(userPo.getUpdateTime());
        return apiUserResponse;
    }

    public ApiUserListResponse convert2ApiUserListResponse(Page<UserPo> userPage) {
        ApiUserListResponse apiUserResponse = new ApiUserListResponse();
        fillApiPage(apiUserResponse, userPage);
        apiUserResponse.setList(userPage.getRecords().stream().map(this::convert2UserResponse).collect(Collectors.toList()));
        return apiUserResponse;
    }

    public static <T> void fillApiPage(ApiOnePageData rst, Page<T> pd) {
        final ApiOnePageDataPagination pagination = new ApiOnePageDataPagination();
        pagination.setTotalSize((int) pd.getTotal());
        pagination.setCurrentPage((int) pd.getCurrent());
        pagination.setPageSize((int) pd.getSize());
        rst.setPagination(pagination);
    }

    public ProjectPo convert2Project(ApiCreateProjectRequest request) {
        final ProjectPo projectPo = new ProjectPo();
        projectPo.setName(request.getName());
        projectPo.setVersion(request.getVersion());
        projectPo.setUid(request.getUid());
        ApiCreateProjectRequest.StatusEnum status = request.getStatus();
        projectPo.setStatus(status.toString());
        projectPo.setRemark(request.getRemark());
        return projectPo;
    }

    public ProjectPo convert2Project(ApiUpdateProjectRequest request) {
        ProjectPo projectPo = new ProjectPo();
        projectPo.setVersion(request.getVersion());
        projectPo.setUid(request.getUid());
        if (null != request.getStatus()) {
            projectPo.setStatus(request.getStatus().toString());
        }
        projectPo.setRemark(request.getRemark());
        return projectPo;
    }

    public ApiProjectResponse convert2ProjectResponse(ProjectBo projectBo) {
        ApiProjectResponse apiProjectResponse = new ApiProjectResponse();
        apiProjectResponse.setId(projectBo.getId());
        apiProjectResponse.setName(projectBo.getName());
        apiProjectResponse.setVersion(projectBo.getVersion());
        apiProjectResponse.setNickName(projectBo.getNickName());
        apiProjectResponse.setStatus(projectBo.getStatus());
        apiProjectResponse.setRemark(projectBo.getRemark());
        apiProjectResponse.setUpdateTime(projectBo.getUpdateTime());
        apiProjectResponse.setCreateTime(projectBo.getCreateTime());
        return apiProjectResponse;
    }

    public ApiProjectListResponse convert2projectListResponse(Page<ProjectBo> projectBoPage) {
        ApiProjectListResponse response = new ApiProjectListResponse();
        fillApiPage(response,projectBoPage);
        response.setList(projectBoPage.getRecords().stream().map(this::convert2ProjectResponse).collect(Collectors.toList()));
        return response;
    }


}
