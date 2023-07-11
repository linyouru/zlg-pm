package com.zlg.zlgpm.helper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.pojo.bo.*;
import com.zlg.zlgpm.pojo.po.*;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类型转换工具
 * 功能：入参校验、Restful包装类和pojo的转换
 *
 * @author linyouru
 */
@Component
public class DataConvertHelper {

    @Resource
    private DataConvertMappingImpl dataConvertMapping;

    public static <T> void fillApiPage(ApiOnePageData rst, Page<T> pd) {
        final ApiOnePageDataPagination pagination = new ApiOnePageDataPagination();
        pagination.setTotalSize((int) pd.getTotal());
        pagination.setCurrentPage((int) pd.getCurrent());
        pagination.setPageSize((int) pd.getSize());
        rst.setPagination(pagination);
    }

    public UserPo convert2UserPo(ApiCreateUserRequest apiCreateUserRequest) {
        UserPo userPo = dataConvertMapping.convertToUserPo(apiCreateUserRequest);
        userPo.setPassword(DigestUtils.md5DigestAsHex(apiCreateUserRequest.getPassword().getBytes()));
        return userPo;
    }

    public UserPo convert2UserPo(ApiUpdateUserRequest apiUpdateUserRequest) {
        UserPo userPo = dataConvertMapping.convertToUserPo(apiUpdateUserRequest);
        String password = apiUpdateUserRequest.getPassword();
        if (null != password) {
            userPo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        }
        return userPo;
    }

    public ApiUserLoginResponse convert2ApiUserLoginResponse(UserPo userPo) {
        return dataConvertMapping.convertToApiUserLoginResponse(userPo);
    }

    public ApiUserResponse convert2ApiUserResponse(UserListBo userListBo) {
        return dataConvertMapping.convertToApiUserResponse(userListBo);
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
        return dataConvertMapping.convertToApiUserByPidResponse(userListBo);
    }

    public ProjectPo convert2ProjectPo(ApiCreateProjectRequest request) {
        ProjectPo projectPo = dataConvertMapping.convertToProjectPo(request);
        ApiCreateProjectRequest.StatusEnum status = request.getStatus();
        projectPo.setStatus(status.toString());
        return projectPo;
    }

    public ProjectPo convert2ProjectPo(ApiUpdateProjectRequest request) {
        ProjectPo projectPo = dataConvertMapping.convertToProjectPo(request);
        if (null != request.getStatus()) {
            projectPo.setStatus(request.getStatus().toString());
        }
        return projectPo;
    }

    public ApiProjectResponse convert2ApiProjectResponse(ProjectBo projectBo) {
        return dataConvertMapping.convertToApiProjectResponse(projectBo);
    }

    public ApiGetProjectByIdResponse convert2ApiGetProjectByIdResponse(ProjectBo projectBo) {
        return dataConvertMapping.convertToApiGetProjectByIdResponse(projectBo);
    }

    public ApiProjectListResponse convert2ApiProjectListResponse(Page<ProjectBo> projectBoPage) {
        ApiProjectListResponse response = new ApiProjectListResponse();
        fillApiPage(response, projectBoPage);
        response.setList(projectBoPage.getRecords().stream().map(this::convert2ApiProjectResponse).collect(Collectors.toList()));
        return response;
    }

    public TaskPo convert2TaskPo(ApiCreateTaskRequest taskRequest) {
        TaskPo taskPo = dataConvertMapping.convertToTaskPo(taskRequest);
        taskPo.setStatus(taskRequest.getStatus().toString());
        taskPo.setTaskType(taskRequest.getTaskType().toString());
        taskPo.setSendEmail2Creator(taskRequest.getSendEmail2Creator() != null ? taskPo.getSendEmail2Creator() : 0);
        return taskPo;
    }

    public TaskPo convert2TaskPo(ApiUpdateTaskRequest taskRequest) {
        TaskPo taskPo = dataConvertMapping.convertToTaskPo(taskRequest);
        taskPo.setTaskType(taskRequest.getTaskType() != null ? taskRequest.getTaskType().toString() : null);
//        taskPo.setStatus(taskRequest.getStatus() != null ? taskRequest.getStatus().toString() : null);
        return taskPo;
    }

    public TaskPo convert2TaskPo(ApiUpdateTaskStatusRequest taskRequest){
        TaskPo taskPo = new TaskPo();
        taskPo.setStatus(taskRequest.getStatus() != null ? taskRequest.getStatus().toString() : null);
        return taskPo;
    }

    public TaskPo convert2TaskPo(ApiAcceptTaskRequest taskRequest) {
        TaskPo taskPo = dataConvertMapping.convertToTaskPo(taskRequest);
        taskPo.setStatus(taskRequest.getStatus() != null ? taskRequest.getStatus().toString() : null);
        return taskPo;
    }

    public ApiTaskListResponse convert2ApiTaskListResponse(Page<TaskListBo> taskListBoPage) {
        ApiTaskListResponse response = new ApiTaskListResponse();
        fillApiPage(response, taskListBoPage);
        response.setList(taskListBoPage.getRecords().stream().map(this::convert2ApiTaskResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiTaskResponse convert2ApiTaskResponse(TaskListBo task) {
        return dataConvertMapping.convertToApiTaskResponse(task);
    }

    public ApiOperationLogResponse convert2ApiOperationLogResponse(OperationLogPo operationLogPo) {
        return dataConvertMapping.convertToApiOperationLogResponse(operationLogPo);
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
        ApiProjectStatisticsResponse apiProjectStatisticsResponse = dataConvertMapping.convertToApiProjectStatisticsResponse(projectStatisticsBo);
        double rateOfFinish = projectStatisticsBo.getTaskTotal() > 0 ? (double) projectStatisticsBo.getFinishTaskNum() / projectStatisticsBo.getTaskTotal() : 0;
        apiProjectStatisticsResponse.setRateOfFinish(String.format("%.2f", rateOfFinish));
        return apiProjectStatisticsResponse;
    }

    public ApiTaskStatisticsResponse convert2ApiTaskStatisticsResponse(TaskStatisticsBo taskStatisticsBo) {
        return dataConvertMapping.convertToApiTaskStatisticsResponse(taskStatisticsBo);
    }

    public TaskChangePo convert2TaskChangePo(ApiCreateTaskChangeRequest taskChangeRequest) {
        return dataConvertMapping.convertToTaskChangePo(taskChangeRequest);
    }

    public ApiTaskChangeListResponse convert2ApiTaskChangeListResponse(Page<TaskChangeListBo> taskChangeListBoPage) {
        ApiTaskChangeListResponse response = new ApiTaskChangeListResponse();
        fillApiPage(response, taskChangeListBoPage);
        response.setList(taskChangeListBoPage.getRecords().stream().map(this::convert2ApiTaskChangeResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiTaskChangeResponse convert2ApiTaskChangeResponse(TaskChangeListBo taskChangeListBo) {
        return dataConvertMapping.convertToApiTaskChangeResponse(taskChangeListBo);
    }

    public TaskLogPo convert2TaskLogPo(ApiCreateTaskLogRequest body) {
        return dataConvertMapping.convertToTaskLogPo(body);
    }

    public TaskLogPo convert2TaskLogPo(ApiUpdateTaskLogRequest body) {
        return dataConvertMapping.convertToTaskLogPo(body);
    }

    public ApiTaskLogListResponse convert2ApiTaskLogListResponse(Page<TaskLogListBo> taskLogListBoPage) {
        ApiTaskLogListResponse response = new ApiTaskLogListResponse();
        fillApiPage(response, taskLogListBoPage);
        response.setList(taskLogListBoPage.getRecords().stream().map(this::convert2ApiTaskLogResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiLastTaskLogResponse convert2ApiLastTaskLogResponse(TaskLogPo taskLogPo) {
        return dataConvertMapping.convertToApiLastTaskLogResponse(taskLogPo);
    }

    public ApiTaskLogResponse convert2ApiTaskLogResponse(TaskLogListBo taskLogListBo) {
        return dataConvertMapping.convertToApiTaskLogResponse(taskLogListBo);
    }

    public ApiTaskLogResponse convert2ApiTaskLogResponse(TaskLogPo taskLogPo) {
        return dataConvertMapping.convertToApiTaskLogResponse(taskLogPo);
    }

    public ApiTaskLogAggregationListResponse convertToAggregationListResponse(Page<TaskLogAggregationListBo> page) {
        ApiTaskLogAggregationListResponse response = new ApiTaskLogAggregationListResponse();
        fillApiPage(response, page);
        response.setList(page.getRecords().stream().map(this::convert2ApiTaskLogAggregationResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiTaskLogAggregationResponse convert2ApiTaskLogAggregationResponse(TaskLogAggregationListBo taskLogAggregationListBo) {
        return dataConvertMapping.convertToApiTaskLogAggregationResponse(taskLogAggregationListBo);
    }

    public List<ApiProjectVersionsResponse> convert2ApiProjectVersionsResponse(List<ProjectVersionPo> projects) {
        ArrayList<ApiProjectVersionsResponse> response = new ArrayList<>();
        projects.forEach(project -> {
            ApiProjectVersionsResponse res = new ApiProjectVersionsResponse();
            res.setId(project.getId());
            res.setVersion(project.getVersion());
            response.add(res);
        });
        return response;
    }

    public TaskFeedbackPo convert2TaskFeedbackPo(ApiCreateFeedbackRequest data) {
        return dataConvertMapping.convertToTaskFeedbackPo(data);
    }

    public TaskFeedbackPo convert2TaskFeedbackPo(ApiUpdateFeedbackRequest data) {
        return dataConvertMapping.convertToTaskFeedbackPo(data);
    }

    public ApiFeedbackListResponse convert2ApiFeedbackListResponse(Page<TaskFeedbackListBo> page) {
        ApiFeedbackListResponse response = new ApiFeedbackListResponse();
        fillApiPage(response, page);
        response.setList(page.getRecords().stream().map(this::convert2ApiFeedbackResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiFeedbackResponse convert2ApiFeedbackResponse(TaskFeedbackListBo feedback) {
        return dataConvertMapping.convertToApiFeedbackResponse(feedback);
    }

    public List<ApiProjectModuleResponse> convert2ApiProjectModuleResponse(List<ProjectModulePo> projectModulePos) {
        ArrayList<ApiProjectModuleResponse> list = new ArrayList<>();
        for (ProjectModulePo projectModulePo : projectModulePos) {
            ApiProjectModuleResponse apiProjectModuleResponse = dataConvertMapping.convertToApiProjectModuleResponse(projectModulePo);
            list.add(apiProjectModuleResponse);
        }
        return list;
    }

    public ProjectVersionPo convert2ProjectVersionPo(ApiCreateProjectVersionRequest body) {
        return dataConvertMapping.convertToProjectVersionPo(body);
    }

    public List<ApiProjectVersionsAllResponse> convert2ApiProjectVersionsAllResponse(List<ProjectVersionBo> projectVersionsAll) {
        ArrayList<ApiProjectVersionsAllResponse> list = new ArrayList<>();
        for (ProjectVersionBo projectVersionBo : projectVersionsAll) {
            ApiProjectVersionsAllResponse apiProjectVersionsAllResponse = dataConvertMapping.convertToApiProjectVersionsAllResponse(projectVersionBo);
            apiProjectVersionsAllResponse.setVid(projectVersionBo.getId());
            apiProjectVersionsAllResponse.setProjectName(projectVersionBo.getName());
            list.add(apiProjectVersionsAllResponse);
        }
        return list;
    }

    public List<ApiUserMessageResponse> convert2ApiUserMessageResponse(List<UserMessageBo> userMessageList) {
        ArrayList<ApiUserMessageResponse> list = new ArrayList<>();
        for (UserMessageBo userMessageBo : userMessageList) {
            ApiUserMessageResponse apiUserMessageResponse = dataConvertMapping.convertToApiUserMessageResponse(userMessageBo);
            list.add(apiUserMessageResponse);
        }
        return list;
    }

    public ApiStatisticTaskResponseList convert2ApiStatisticTaskResponseList(Page<StatisticTaskBo> statisticTaskBoPage){
        ApiStatisticTaskResponseList response = new ApiStatisticTaskResponseList();
        fillApiPage(response, statisticTaskBoPage);
        response.setList(statisticTaskBoPage.getRecords().stream().map(this::convert2ApiStatisticTaskResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiStatisticTaskResponse convert2ApiStatisticTaskResponse(StatisticTaskBo statisticTask){
        return dataConvertMapping.convertToApiStatisticTaskResponse(statisticTask);
    }

    public ApiStatisticLogResponseList convert2ApiStatisticLogResponseList(Page<StatisticLogBo> statisticLogBoPage){
        ApiStatisticLogResponseList response = new ApiStatisticLogResponseList();
        fillApiPage(response,statisticLogBoPage);
        response.setList(statisticLogBoPage.getRecords().stream().map(this::convert2ApiStatisticLogResponse).collect(Collectors.toList()));
        return response;
    }

    public ApiStatisticLogResponse convert2ApiStatisticLogResponse(StatisticLogBo statisticLog){
        return dataConvertMapping.convertToApiStatisticLogResponse(statisticLog);
    }

}
