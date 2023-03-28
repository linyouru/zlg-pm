package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.bo.ProjectStatisticsBo;
import com.zlg.zlgpm.pojo.po.ProjectModulePo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.po.UserProjectPo;
import com.zlg.zlgpm.service.ProjectModuleService;
import com.zlg.zlgpm.service.ProjectService;
import com.zlg.zlgpm.service.UserProjectService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "project")
public class ProjectController implements ProjectApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ProjectService projectService;
    @Resource
    private DataConvertHelper dataConvertHelper;
    @Resource
    private UserProjectService userProjectService;
    @Resource
    private ProjectModuleService projectModuleService;

    @Override
//    @RequiresRoles(value = "root")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiBaseResp> createProject(ApiCreateProjectRequest body) {
        ProjectPo project = projectService.createProject(body);
        //添加项目和成员关系
        String memberUid = body.getMemberUid();
        if (null != memberUid) {
            List<UserProjectPo> list = generateUserProjectPoList(memberUid, project.getUid(), project.getId());
            userProjectService.saveBatch(list);
        }
        //添加项目功能块
        ArrayList<ProjectModulePo> list = batchProjectModule(body.getModule(), project.getId());
        projectModuleService.saveBatch(list);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> updateProject(Integer id, ApiUpdateProjectRequest body) {
        ProjectPo updateProjectPo = dataConvertHelper.convert2ProjectPo(body);
        updateProjectPo.setId(id);
        ProjectPo projectPo = projectService.updateProject(updateProjectPo);

        //更新项目成员
        String memberUid = body.getMemberUid();
        if (StringUtils.hasText(memberUid)) {
            userProjectService.deleteProjectUser(id);
            List<UserProjectPo> list = generateUserProjectPoList(memberUid, projectPo.getUid(), id);
            userProjectService.saveBatch(list);
        }
        //创建,更新,删除项目模块
        if(StringUtils.hasText(body.getModule())){
            ArrayList<ProjectModulePo> list = batchProjectModule(body.getModule(), id);
            ArrayList<ProjectModulePo> createList = new ArrayList<>();
            for (ProjectModulePo projectModulePo : list) {
                if(null==projectModulePo.getId()){
                    createList.add(projectModulePo);
                }
            }
            projectModuleService.updateBatchById(list);
            projectModuleService.deleteProjectModuleForUpdate(list,id);
            projectModuleService.saveBatch(createList);
        }
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }



    @Override
    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> deleteProject(Integer id) {
        projectService.deleteProject(id);
        userProjectService.deleteProjectUser(id);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    public ResponseEntity<ApiProjectResponse> getProjectById(Integer id) {
        ProjectBo projectById = projectService.getProjectById(id);
        ApiProjectResponse response = dataConvertHelper.convert2ApiProjectResponse(projectById);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<List<ApiProjectVersionsResponse>> getProjectVersions(String projectName) {
        List<ProjectBo> projectVersions = projectService.getProjectVersions(projectName);
        List<ApiProjectVersionsResponse> responses = dataConvertHelper.convert2ApiProjectVersionsResponse(projectVersions);
        return ResponseEntity.ok().body(responses);
    }

    @Override
    public ResponseEntity<List<String>> projectGroupName() {
        List<Map<String, String>> maps = projectService.aggregatedProjectName();
        List<String> responses = dataConvertHelper.convert2List(maps, "name");
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<ApiProjectListResponse> projectList(String name, Integer currentPage, Integer pageSize) {
        Page<ProjectBo> projectBoPage = projectService.projectList(name, currentPage, pageSize);
        ApiProjectListResponse apiProjectListResponse = dataConvertHelper.convert2ApiProjectListResponse(projectBoPage);
        return ResponseEntity.ok().body(apiProjectListResponse);
    }

    @Override
    public ResponseEntity<ApiProjectStatisticsListResponse> projectStatistics(Integer currentPage, Integer pageSize) {
        Page<ProjectStatisticsBo> projectStatisticsBoPage = projectService.selectProjectStatistics(currentPage, pageSize);
        ApiProjectStatisticsListResponse apiProjectStatisticsListResponse = dataConvertHelper.convert2ApiProjectStatisticsListResponse(projectStatisticsBoPage);
        return ResponseEntity.ok().body(apiProjectStatisticsListResponse);
    }

    @Override
    public ResponseEntity<List<ApiProjectModuleResponse>> queryProjectModule(Integer id) {
        List<ProjectModulePo> projectModulePos = projectModuleService.queryProjectModule(id);
        List<ApiProjectModuleResponse> response = dataConvertHelper.convert2ApiProjectModuleResponse(projectModulePos);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Boolean> queryProjectModuleHaveTask(Integer id) {
        boolean b = projectModuleService.queryProjectModuleHaveTask(id);
        return ResponseEntity.ok(b);
    }

    private List<UserProjectPo> generateUserProjectPoList(String uids, int ownerId, int pid) {
        ArrayList<UserProjectPo> list = new ArrayList<>();

        String[] memberUids = uids.split(",");
        ArrayList<String> memberUidList = new ArrayList<>();
        Collections.addAll(memberUidList, memberUids);
        if (!memberUidList.contains(ownerId + "")) {
            memberUidList.add(ownerId + "");
        }
        for (String uid : memberUidList) {
            UserProjectPo userProjectPo = new UserProjectPo(Integer.parseInt(uid), pid);
            list.add(userProjectPo);
        }
        return list;
    }

    private ArrayList<ProjectModulePo> batchProjectModule(String moduleStr, int pid){
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<ProjectModulePo> list;
        try {
            list = mapper.readValue(moduleStr, mapper.getTypeFactory().constructParametricType(ArrayList.class, ProjectModulePo.class));
        } catch (JsonProcessingException e) {
            logger.error("createProject json analysis error",e);
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11004");
        }
        for (ProjectModulePo pmp : list) {
            pmp.setPid(pid);
        }
       return list;
    }
}
