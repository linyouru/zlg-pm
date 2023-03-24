package com.zlg.zlgpm.helper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.pojo.bo.*;
import com.zlg.zlgpm.pojo.po.*;
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
        userPo.setCustom(apiUpdateUserRequest.getCustom());
        return userPo;
    }

    public ApiUserLoginResponse convert2ApiUserLoginResponse(UserPo userPo) {
        ApiUserLoginResponse apiUserResponse = new ApiUserLoginResponse();
        apiUserResponse.setId(Math.toIntExact(userPo.getId()));
        apiUserResponse.setUserName(userPo.getUserName());
        apiUserResponse.setNickName(userPo.getNickName());
        apiUserResponse.setEmail(userPo.getEmail());
        apiUserResponse.setRemark(userPo.getRemark());
        apiUserResponse.setCreateTime(userPo.getCreateTime());
        apiUserResponse.setUpdateTime(userPo.getUpdateTime());
        apiUserResponse.setCustom(userPo.getCustom());
        return apiUserResponse;
    }

    public ApiUserLoginResponse convert2ApiUserResponse(UserPo userPo) {
        ApiUserLoginResponse apiUserLoginResponse = new ApiUserLoginResponse();
        apiUserLoginResponse.setId(Math.toIntExact(userPo.getId()));
        apiUserLoginResponse.setUserName(userPo.getUserName());
        apiUserLoginResponse.setNickName(userPo.getNickName());
        apiUserLoginResponse.setEmail(userPo.getEmail());
        apiUserLoginResponse.setRemark(userPo.getRemark());
        apiUserLoginResponse.setCreateTime(userPo.getCreateTime());
        apiUserLoginResponse.setUpdateTime(userPo.getUpdateTime());
        apiUserLoginResponse.setCustom(userPo.getCustom());
        return apiUserLoginResponse;
    }

    public ApiUserResponse convert2ApiUserResponse(UserListBo userListBo) {
        ApiUserResponse apiUserResponse = new ApiUserResponse();
        apiUserResponse.setId(Math.toIntExact(userListBo.getId()));
        apiUserResponse.setUserName(userListBo.getUserName());
        apiUserResponse.setNickName(userListBo.getNickName());
        apiUserResponse.setEmail(userListBo.getEmail());
        apiUserResponse.setRemark(userListBo.getRemark());
        apiUserResponse.setCreateTime(userListBo.getCreateTime());
        apiUserResponse.setUpdateTime(userListBo.getUpdateTime());
        apiUserResponse.setCustom(userListBo.getCustom());
        apiUserResponse.setTaskTotal(userListBo.getTaskTotal());
        apiUserResponse.setTaskFinishCount(userListBo.getTaskFinishCount());
        apiUserResponse.setTaskTimelyCount(userListBo.getTaskTimelyCount());
        apiUserResponse.setTaskTimeoutCount(userListBo.getTaskTimeoutCount());
        apiUserResponse.setTimelinessRate(userListBo.getTimelinessRate());
        return apiUserResponse;
    }

    public ApiUserListResponse convert2ApiUserListResponse(Page<UserListBo> userListBoPage) {
        ApiUserListResponse apiUserResponse = new ApiUserListResponse();
        fillApiPage(apiUserResponse, userListBoPage);
        apiUserResponse.setList(userListBoPage.getRecords().stream().map(this::convert2ApiUserResponse).collect(Collectors.toList()));
        return apiUserResponse;
    }

    public ApiUserListByPidResponse convert2ApiUserListByPidResponse(Page<UserListBo> userListBoPage) {
        ApiUserListByPidResponse apiUserListByPidResponse = new ApiUserListByPidResponse();
        fillApiPage(apiUserListByPidResponse, userListBoPage);
        apiUserListByPidResponse.setList(userListBoPage.getRecords().stream().map(this::convert2ApiUserByPidResponse).collect(Collectors.toList()));
        return apiUserListByPidResponse;
    }

    public ApiUserByPidResponse convert2ApiUserByPidResponse(UserListBo userListBo) {
        ApiUserByPidResponse apiUserByPidResponse = new ApiUserByPidResponse();
        apiUserByPidResponse.setId(Math.toIntExact(userListBo.getId()));
        apiUserByPidResponse.setUserName(userListBo.getUserName());
        apiUserByPidResponse.setNickName(userListBo.getNickName());
        apiUserByPidResponse.setEmail(userListBo.getEmail());
        apiUserByPidResponse.setRemark(userListBo.getRemark());
        apiUserByPidResponse.setCreateTime(userListBo.getCreateTime());
        apiUserByPidResponse.setUpdateTime(userListBo.getUpdateTime());
        apiUserByPidResponse.setCustom(userListBo.getCustom());
        return apiUserByPidResponse;
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
        if(null != request.getUid()){
            projectPo.setUid(request.getUid());
        }
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
        apiProjectResponse.setUid(projectBo.getUid());
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
        ApiCreateTaskRequest.TimelyEnum timely = taskRequest.getTimely();
        task.setTimely(timely != null ? timely.toString() : null);
        task.setQuality(taskRequest.getQuality());
        task.setDocument(taskRequest.getDocument());
        task.setRemark(taskRequest.getRemark());
        task.setLink(taskRequest.getLink());
        task.setMid(taskRequest.getMid());
        task.setHaveDocument(taskRequest.getHaveDocument());
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
        ApiUpdateTaskRequest.TimelyEnum timely = taskRequest.getTimely();
        task.setTimely(timely != null ? timely.toString() : null);
        task.setQuality(taskRequest.getQuality());
        task.setDocument(taskRequest.getDocument());
        task.setRemark(taskRequest.getRemark());
        task.setLink(taskRequest.getLink());
        task.setMid(taskRequest.getMid());
        task.setHaveDocument(taskRequest.getHaveDocument());
        task.setAcceptanceTime(taskRequest.getAcceptanceTime());
        task.setPid(taskRequest.getPid());
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
        response.setTimely(task.getTimely());
        response.setQuality(task.getQuality());
        response.setDocument(task.getDocument());
        response.setRemark(task.getRemark());
        response.setLink(task.getLink());
        response.setModule(task.getModule());
        response.setModuleLevel(task.getLevel());
        response.setWarning(task.getWarning());
        response.setOvertime(task.getOvertime());
        response.setWorkTimeCount(task.getWorkTimeCount());
        response.setProgress(task.getProgress());
        response.setHaveDocument(task.getHaveDocument());
        response.setPid(task.getPid());
        response.setUid(task.getUid());
        response.setCreatedUid(task.getCreatedUid());
        response.setProjectUid(task.getProjectUid());
        response.setCreatedUserNickname(task.getCreatedUserNickname());
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

    public List<String> convert2List(List<Map<String, String>> maps, String key) {
        ArrayList<String> list = new ArrayList<>();
        for (Map<String, String> map : maps) {
            list.add(map.get(key));
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

    public TaskChangePo convert2TaskChangePo(ApiCreateTaskChangeRequest taskChangeRequest) {
        TaskChangePo taskChangePo = new TaskChangePo();
        taskChangePo.setTaskId(taskChangeRequest.getTaskId());
        taskChangePo.setUid(taskChangeRequest.getUid());
        taskChangePo.setStatus(taskChangeRequest.getStatus());
        taskChangePo.setBeforeStartTime(taskChangeRequest.getBeforeStartTime());
        taskChangePo.setBeforeEndTime(taskChangeRequest.getBeforeEndTime());
        taskChangePo.setAfterStartTime(taskChangeRequest.getAfterStartTime());
        taskChangePo.setAfterEndTime(taskChangeRequest.getAfterEndTime());
        taskChangePo.setReason(taskChangeRequest.getReason());
        return taskChangePo;
    }

    public ApiTaskChangeListResponse convert2ApiTaskChangeListResponse(Page<TaskChangeListBo> taskChangeListBoPage) {
        ApiTaskChangeListResponse response = new ApiTaskChangeListResponse();
        fillApiPage(response, taskChangeListBoPage);
        response.setList(taskChangeListBoPage.getRecords().stream().map(this::convert2ApiTaskChangeResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiTaskChangeResponse convert2ApiTaskChangeResponse(TaskChangeListBo taskChangeListBo) {
        ApiTaskChangeResponse apiTaskChangeResponse = new ApiTaskChangeResponse();
        apiTaskChangeResponse.setId(taskChangeListBo.getId());
        apiTaskChangeResponse.setTaskId(taskChangeListBo.getTaskId());
        apiTaskChangeResponse.setTask(taskChangeListBo.getTask());
        apiTaskChangeResponse.setUid(taskChangeListBo.getUid());
        apiTaskChangeResponse.setUserName(taskChangeListBo.getUserName());
        apiTaskChangeResponse.setNickName(taskChangeListBo.getNickName());
        apiTaskChangeResponse.setStatus(taskChangeListBo.getStatus());
        apiTaskChangeResponse.setAuditorId(taskChangeListBo.getAuditorId());
        apiTaskChangeResponse.setAuditorName(taskChangeListBo.getAuditorName());
        apiTaskChangeResponse.setBeforeStartTime(taskChangeListBo.getBeforeStartTime());
        apiTaskChangeResponse.setBeforeEndTime(taskChangeListBo.getBeforeEndTime());
        apiTaskChangeResponse.setAfterStartTime(taskChangeListBo.getAfterStartTime());
        apiTaskChangeResponse.setAfterEndTime(taskChangeListBo.getAfterEndTime());
        apiTaskChangeResponse.setReason(taskChangeListBo.getReason());
        apiTaskChangeResponse.setCreateTime(taskChangeListBo.getCreateTime());
        apiTaskChangeResponse.setProjectName(taskChangeListBo.getProjectName());
        apiTaskChangeResponse.setProjectVersion(taskChangeListBo.getProjectVersion());
        return apiTaskChangeResponse;
    }

    public TaskLogPo convert2TaskLogPo(ApiCreateTaskLogRequest body) {
        TaskLogPo taskLogPo = new TaskLogPo();
        taskLogPo.setTaskId(body.getTaskId());
        taskLogPo.setUid(body.getUid());
        taskLogPo.setWorkTime(body.getWorkTime());
        taskLogPo.setProgress(body.getProgress());
        taskLogPo.setLog(body.getLog());
        return taskLogPo;
    }

    public ApiTaskLogListResponse convert2ApiTaskLogListResponse(Page<TaskLogListBo> taskLogListBoPage) {
        ApiTaskLogListResponse response = new ApiTaskLogListResponse();
        fillApiPage(response, taskLogListBoPage);
        response.setList(taskLogListBoPage.getRecords().stream().map(this::convert2ApiTaskLogResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiLastTaskLogResponse convert2ApiLastTaskLogResponse(TaskLogPo taskLogPo) {
        ApiLastTaskLogResponse response = new ApiLastTaskLogResponse();
        response.setId(taskLogPo.getId());
        response.setTaskId(taskLogPo.getTaskId());
        response.setUid(taskLogPo.getUid());
        response.setWorkTime(taskLogPo.getWorkTime());
        response.setProgress(taskLogPo.getProgress());
        response.setLog(taskLogPo.getLog());
        response.setCreateTime(taskLogPo.getCreateTime());
        return response;
    }

    public ApiTaskLogResponse convert2ApiTaskLogResponse(TaskLogListBo taskLogListBo) {
        ApiTaskLogResponse apiTaskLogResponse = new ApiTaskLogResponse();
        apiTaskLogResponse.setId(taskLogListBo.getId());
        apiTaskLogResponse.setTaskId(taskLogListBo.getTaskId());
        apiTaskLogResponse.setUid(taskLogListBo.getUid());
        apiTaskLogResponse.setUserName(taskLogListBo.getUserName());
        apiTaskLogResponse.setNickName(taskLogListBo.getNickName());
        apiTaskLogResponse.setWorkTime(taskLogListBo.getWorkTime());
        apiTaskLogResponse.setProgress(taskLogListBo.getProgress());
        apiTaskLogResponse.setLog(taskLogListBo.getLog());
        apiTaskLogResponse.setCreateTime(taskLogListBo.getCreateTime());
        return apiTaskLogResponse;
    }

    public ApiTaskLogAggregationListResponse convertToAggregationListResponse(Page<TaskLogAggregationListBo> page) {
        ApiTaskLogAggregationListResponse response = new ApiTaskLogAggregationListResponse();
        fillApiPage(response, page);
        response.setList(page.getRecords().stream().map(this::convert2ApiTaskLogAggregationResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiTaskLogAggregationResponse convert2ApiTaskLogAggregationResponse(TaskLogAggregationListBo taskLogAggregationListBo) {
        ApiTaskLogAggregationResponse response = new ApiTaskLogAggregationResponse();
        response.setId(taskLogAggregationListBo.getId());
        response.setUid(taskLogAggregationListBo.getUid());
        response.setUserName(taskLogAggregationListBo.getUserName());
        response.setNickName(taskLogAggregationListBo.getNickName());
        response.setTaskId(taskLogAggregationListBo.getTaskId());
        response.setName(taskLogAggregationListBo.getName());
        response.setVersion(taskLogAggregationListBo.getVersion());
        response.setLog(taskLogAggregationListBo.getLog());
        response.setCreateTime(taskLogAggregationListBo.getCreateTime());
        return response;
    }

    public List<ApiProjectVersionsResponse> convert2ApiProjectVersionsResponse(List<ProjectBo> projects) {
        ArrayList<ApiProjectVersionsResponse> response = new ArrayList<>();
        projects.forEach(project -> {
            ApiProjectVersionsResponse res = new ApiProjectVersionsResponse();
            res.setId(project.getId());
            res.setName(project.getName());
            res.setVersion(project.getVersion());
            response.add(res);
        });
        return response;
    }

    public TaskFeedbackPo convert2TaskFeedbackPo(ApiCreateFeedbackRequest data) {
        TaskFeedbackPo response = new TaskFeedbackPo();
        response.setFeedback(data.getFeedback());
        response.setUid(data.getUid());
        response.setTid(data.getTid());
        response.setRemark(data.getRemark());
        return response;
    }

    public TaskFeedbackPo convert2TaskFeedbackPo(ApiUpdateFeedbackRequest data) {
        TaskFeedbackPo response = new TaskFeedbackPo();
        response.setFeedback(data.getFeedback());
        response.setUid(data.getUid());
        response.setRemark(data.getRemark());
        return response;
    }

    public ApiFeedbackListResponse convert2ApiFeedbackListResponse(Page<TaskFeedbackListBo> page) {
        ApiFeedbackListResponse response = new ApiFeedbackListResponse();
        fillApiPage(response, page);
        response.setList(page.getRecords().stream().map(this::convert2ApiFeedbackResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiFeedbackResponse convert2ApiFeedbackResponse(TaskFeedbackListBo feedback) {
        ApiFeedbackResponse response = new ApiFeedbackResponse();
        response.setFeedback(feedback.getFeedback());
        response.setId(feedback.getId());
        response.setTid(feedback.getTid());
        response.setUid(feedback.getUid());
        response.setNickName(feedback.getNickName());
        response.setRemark(feedback.getRemark());
        response.setCreateTime(feedback.getCreateTime());
        response.setUpdateTime(feedback.getUpdateTime());
        return response;
    }

    public List<ApiProjectModuleResponse> convert2ApiProjectModuleResponse(List<ProjectModulePo> projectModulePos){
        ArrayList<ApiProjectModuleResponse> list = new ArrayList<>();
        for (ProjectModulePo projectModulePo : projectModulePos) {
            ApiProjectModuleResponse projectModuleResponse = new ApiProjectModuleResponse();
            projectModuleResponse.setId(projectModulePo.getId());
            projectModuleResponse.setPid(projectModulePo.getPid());
            projectModuleResponse.setModule(projectModulePo.getModule());
            projectModuleResponse.setLevel(projectModulePo.getLevel());

            list.add(projectModuleResponse);
        }
        return list;
    }


}
