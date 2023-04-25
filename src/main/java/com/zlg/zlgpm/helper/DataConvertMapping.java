package com.zlg.zlgpm.helper;

import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.pojo.bo.*;
import com.zlg.zlgpm.pojo.po.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DataConvertMapping {

    UserPo convertToUserPo(ApiCreateUserRequest apiCreateUserRequest);
    UserPo convertToUserPo(ApiUpdateUserRequest apiUpdateUserRequest);
    ApiUserLoginResponse convertToApiUserLoginResponse(UserPo userPo);
    ApiUserResponse convertToApiUserResponse(UserListBo userListBo);
    ApiUserByPidResponse convertToApiUserByPidResponse(UserListBo userListBo);
    ApiProjectResponse convertToApiProjectResponse(ProjectBo projectBo);
    ApiGetProjectByIdResponse convertToApiGetProjectByIdResponse(ProjectBo projectBo);
    ApiTaskResponse convertToApiTaskResponse(TaskListBo task);
    ApiOperationLogResponse convertToApiOperationLogResponse(OperationLogPo operationLogPo);
    ApiTaskStatisticsResponse convertToApiTaskStatisticsResponse(TaskStatisticsBo taskStatisticsBo);
    TaskChangePo convertToTaskChangePo(ApiCreateTaskChangeRequest taskChangeRequest);
    ApiTaskChangeResponse convertToApiTaskChangeResponse(TaskChangeListBo taskChangeListBo);
    TaskLogPo convertToTaskLogPo(ApiCreateTaskLogRequest body);
    ApiLastTaskLogResponse convertToApiLastTaskLogResponse(TaskLogPo taskLogPo);
    ApiTaskLogResponse convertToApiTaskLogResponse(TaskLogListBo taskLogListBo);
    ApiTaskLogAggregationResponse convertToApiTaskLogAggregationResponse(TaskLogAggregationListBo taskLogAggregationListBo);
    TaskFeedbackPo convertToTaskFeedbackPo(ApiCreateFeedbackRequest data);
    TaskFeedbackPo convertToTaskFeedbackPo(ApiUpdateFeedbackRequest data);
    ApiFeedbackResponse convertToApiFeedbackResponse(TaskFeedbackListBo feedback);
    ProjectVersionPo convertToProjectVersionPo(ApiCreateProjectVersionRequest body);
    ProjectPo convertToProjectPo(ApiCreateProjectRequest request);
    ProjectPo convertToProjectPo(ApiUpdateProjectRequest request);
    TaskPo convertToTaskPo(ApiCreateTaskRequest taskRequest);
    TaskPo convertToTaskPo(ApiUpdateTaskRequest taskRequest);
    ApiProjectStatisticsResponse convertToApiProjectStatisticsResponse(ProjectStatisticsBo projectStatisticsBo);
    ApiProjectModuleResponse convertToApiProjectModuleResponse(ProjectModulePo projectModulePo);
    ApiProjectVersionsAllResponse convertToApiProjectVersionsAllResponse(ProjectVersionBo projectVersionBo);
    ApiUserMessageResponse convertToApiUserMessageResponse(UserMessageBo userMessageBo);
}
