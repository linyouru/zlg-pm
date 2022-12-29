package com.zlg.zlgpm.helper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.bo.ProjectStatisticsBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.TaskStatisticsBo;
import com.zlg.zlgpm.pojo.po.OperationLogPo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类型转换工具
 *
 * @author linyouru
 */
@Component
public class DataConvertHelper {


    public UserPo convert2UserPo(ApiCreateUserRequest apiCreateUserRequest) {
        final UserPo userPo = new UserPo();
        String password = apiCreateUserRequest.getPassword();
        userPo.setUserName(apiCreateUserRequest.getUserName());
        userPo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userPo.setNickName(apiCreateUserRequest.getNickName());
        userPo.setEmail(apiCreateUserRequest.getEmail());
        userPo.setRemark(apiCreateUserRequest.getRemark());
        return userPo;
    }

    public UserPo convert2UserPo(ApiUpdateUserRequest apiUpdateUserRequest) {
        UserPo userPo = new UserPo();
        String password = apiUpdateUserRequest.getPassword();
        if (null != password) {
            userPo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        }
        userPo.setNickName(apiUpdateUserRequest.getNickName());
        userPo.setEmail(apiUpdateUserRequest.getEmail());
        userPo.setRemark(apiUpdateUserRequest.getRemark());
        userPo.setTaskTitle(apiUpdateUserRequest.getTaskTitle());
        return userPo;
    }

    public ApiUserResponse convert2ApiUserResponse(UserPo userPo) {
        ApiUserResponse apiUserResponse = new ApiUserResponse();
        apiUserResponse.setId(Math.toIntExact(userPo.getId()));
        apiUserResponse.setUserName(userPo.getUserName());
        apiUserResponse.setNickName(userPo.getNickName());
        apiUserResponse.setEmail(userPo.getEmail());
        apiUserResponse.setRemark(userPo.getRemark());
        apiUserResponse.setCreateTime(userPo.getCreateTime());
        apiUserResponse.setUpdateTime(userPo.getUpdateTime());
        apiUserResponse.setTaskTitle(userPo.getTaskTitle());
        return apiUserResponse;
    }

    public ApiUserListResponse convert2ApiUserListResponse(Page<UserPo> userPage) {
        ApiUserListResponse apiUserResponse = new ApiUserListResponse();
        fillApiPage(apiUserResponse, userPage);
        apiUserResponse.setList(userPage.getRecords().stream().map(this::convert2ApiUserResponse).collect(Collectors.toList()));
        return apiUserResponse;
    }

    public static <T> void fillApiPage(ApiOnePageData rst, Page<T> pd) {
        final ApiOnePageDataPagination pagination = new ApiOnePageDataPagination();
        pagination.setTotalSize((int) pd.getTotal());
        pagination.setCurrentPage((int) pd.getCurrent());
        pagination.setPageSize((int) pd.getSize());
        rst.setPagination(pagination);
    }

    public ProjectPo convert2ProjectPo(ApiCreateProjectRequest request) {
        final ProjectPo projectPo = new ProjectPo();
        projectPo.setName(request.getName());
        projectPo.setVersion(request.getVersion());
        projectPo.setUid(request.getUid());
        ApiCreateProjectRequest.StatusEnum status = request.getStatus();
        projectPo.setStatus(status.toString());
        projectPo.setRemark(request.getRemark());
        return projectPo;
    }

    public ProjectPo convert2ProjectPo(ApiUpdateProjectRequest request) {
        ProjectPo projectPo = new ProjectPo();
        projectPo.setVersion(request.getVersion());
        projectPo.setUid(request.getUid());
        if (null != request.getStatus()) {
            projectPo.setStatus(request.getStatus().toString());
        }
        projectPo.setRemark(request.getRemark());
        return projectPo;
    }

    public ApiProjectResponse convert2ApiProjectResponse(ProjectBo projectBo) {
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

    public ApiProjectListResponse convert2ApiProjectListResponse(Page<ProjectBo> projectBoPage) {
        ApiProjectListResponse response = new ApiProjectListResponse();
        fillApiPage(response, projectBoPage);
        response.setList(projectBoPage.getRecords().stream().map(this::convert2ApiProjectResponse).collect(Collectors.toList()));
        return response;
    }

    public TaskPo convert2TaskPo(ApiCreateTaskRequest taskRequest) {
        TaskPo task = new TaskPo();
        task.setPid(taskRequest.getPid());
        task.setUid(taskRequest.getUid());
        task.setTaskType(taskRequest.getTaskType().toString());
        task.setTask(taskRequest.getTask());
        task.setStatus(taskRequest.getStatus().toString());
        task.setPlayStartTime(StringUtils.hasText(taskRequest.getPlayStartTime()) ? taskRequest.getPlayStartTime() : null);
        task.setPlayEndTime(StringUtils.hasText(taskRequest.getPlayEndTime()) ? taskRequest.getPlayEndTime() : null);
        task.setStartTime(taskRequest.getStartTime());
        task.setEndTime(taskRequest.getEndTime());
        ApiCreateTaskRequest.TimelyEnum timely = taskRequest.getTimely();
        task.setTimely(timely != null ? timely.toString() : null);
        task.setQuality(taskRequest.getQuality());
        task.setDocument(taskRequest.getDocument());
        task.setRemark(taskRequest.getRemark());
        task.setLink(taskRequest.getLink());
        task.setModule(taskRequest.getModule());
        return task;
    }

    public TaskPo convert2TaskPo(ApiUpdateTaskRequest taskRequest) {
        TaskPo task = new TaskPo();
        task.setUid(taskRequest.getUid());
        ApiUpdateTaskRequest.TaskTypeEnum taskType = taskRequest.getTaskType();
        task.setTaskType(taskType != null ? taskType.toString() : null);
        task.setTask(taskRequest.getTask());
        ApiUpdateTaskRequest.StatusEnum status = taskRequest.getStatus();
        task.setStatus(status != null ? status.toString() : null);
        task.setPlayStartTime(taskRequest.getPlayStartTime());
        task.setPlayEndTime(taskRequest.getPlayEndTime());
        task.setStartTime(taskRequest.getStartTime());
        task.setEndTime(taskRequest.getEndTime());
        ApiUpdateTaskRequest.TimelyEnum timely = taskRequest.getTimely();
        task.setTimely(timely != null ? timely.toString() : null);
        task.setQuality(taskRequest.getQuality());
        task.setDocument(taskRequest.getDocument());
        task.setRemark(taskRequest.getRemark());
        task.setLink(taskRequest.getLink());
        task.setModule(taskRequest.getModule());
        return task;
    }

    public ApiTaskListResponse convert2ApiTaskListResponse(Page<TaskListBo> taskListBoPage) {
        ApiTaskListResponse response = new ApiTaskListResponse();
        fillApiPage(response, taskListBoPage);
        response.setList(taskListBoPage.getRecords().stream().map(this::convert2ApiTaskResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiTaskResponse convert2ApiTaskResponse(TaskListBo task) {
        ApiTaskResponse response = new ApiTaskResponse();
        response.setId(task.getId());
        response.setProjectName(task.getProjectName());
        response.setProjectVersion(task.getProjectVersion());
        response.setTaskType(task.getTaskType());
        response.setTask(task.getTask());
        response.setStatus(task.getStatus());
        response.setNickName(task.getNickName());
        response.setPlayStartTime(task.getPlayStartTime());
        response.setPlayEndTime(task.getPlayEndTime());
        response.setStartTime(task.getStartTime());
        response.setEndTime(task.getEndTime());
        response.setTimely(task.getTimely());
        response.setQuality(task.getQuality());
        response.setDocument(task.getDocument());
        response.setRemark(task.getRemark());
        response.setLink(task.getLink());
        response.setModule(task.getModule());
        response.setWarning(task.getWarning());
        response.setOvertime(task.getOvertime());
        return response;
    }

    public ApiOperationLogResponse convert2ApiOperationLogResponse(OperationLogPo operationLogPo) {
        ApiOperationLogResponse response = new ApiOperationLogResponse();
        response.setId(operationLogPo.getId());
        response.setUid(operationLogPo.getUid());
        response.setUserName(operationLogPo.getUserName());
        response.setRecord(operationLogPo.getRecord());
        response.setCreateTime(operationLogPo.getCreateTime());
        return response;
    }

    public ApiOperationLogListResponse convert2ApiOperationLogListResponse(Page<OperationLogPo> operationLogPoPage) {
        ApiOperationLogListResponse response = new ApiOperationLogListResponse();
        fillApiPage(response, operationLogPoPage);
        response.setList(operationLogPoPage.getRecords().stream().map(this::convert2ApiOperationLogResponse).collect(Collectors.toList()));
        return response;
    }

    public List<String> convert2ApiProjectGroupNameResponse(List<Map<String, String>> maps) {
        ArrayList<String> list = new ArrayList<>();
        for (Map<String, String> map : maps) {
            list.add(map.get("name"));
        }
        return list;
    }

    public List<String> convert2ApiTaskGroupModuleResponse(List<Map<String, String>> maps) {
        ArrayList<String> list = new ArrayList<>();
        for (Map<String, String> map : maps) {
            list.add(map.get("module"));
        }
        return list;
    }

    public ApiProjectStatisticsListResponse convert2ApiProjectStatisticsListResponse(Page<ProjectStatisticsBo> projectStatisticsBoPage) {
        ApiProjectStatisticsListResponse response = new ApiProjectStatisticsListResponse();
        fillApiPage(response, projectStatisticsBoPage);
        response.setList(projectStatisticsBoPage.getRecords().stream().map(this::convert2ApiProjectStatisticsResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiProjectStatisticsResponse convert2ApiProjectStatisticsResponse(ProjectStatisticsBo projectStatisticsBo) {
        ApiProjectStatisticsResponse response = new ApiProjectStatisticsResponse();
        response.setId(projectStatisticsBo.getId());
        response.setName(projectStatisticsBo.getName());
        response.setVersion(projectStatisticsBo.getVersion());
        response.setTaskTotal(projectStatisticsBo.getTaskTotal());
        double rateOfFinish = projectStatisticsBo.getTaskTotal() > 0 ? (double) projectStatisticsBo.getFinishTaskNum() / projectStatisticsBo.getTaskTotal() : 0;
        response.setRateOfFinish(String.format("%.2f", rateOfFinish));
        return response;
    }

    public ApiTaskStatisticsResponse convert2ApiTaskStatisticsResponse(TaskStatisticsBo taskStatisticsBo) {
        ApiTaskStatisticsResponse response = new ApiTaskStatisticsResponse();
        response.setTaskTotal(taskStatisticsBo.getTaskTotal());
        response.setFinishTaskNum(taskStatisticsBo.getFinishTaskNum());
        response.setRateOfFinish(taskStatisticsBo.getRateOfFinish());
        response.setOvertimeTaskNum(taskStatisticsBo.getOvertimeTaskNum());
        response.setWarningTaskNum(taskStatisticsBo.getWarningTaskNum());
        response.setProgressTaskNum(taskStatisticsBo.getProgressTaskNum());
        return response;
    }
}
