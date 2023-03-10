package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.bo.ProjectStatisticsBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.po.UserProjectPo;
import com.zlg.zlgpm.service.ProjectService;
import com.zlg.zlgpm.service.UserProjectService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
//    @RequiresRoles(value = "root")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiBaseResp> createProject(ApiCreateProjectRequest body) {
        ProjectPo project = projectService.createProject(body);
        //添加项目和成员关系
        String memberUid = body.getMemberUid();
        if(null != memberUid){
            List<UserProjectPo> list = generateUserProjectPoList(memberUid, project.getUid(), project.getId());
            userProjectService.saveBatch(list);
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
    @Transactional(rollbackFor = Exception.class)
//    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> updateProject(Integer id, ApiUpdateProjectRequest body) {
        ProjectPo projectPo = dataConvertHelper.convert2ProjectPo(body);
        projectPo.setId(id);
        projectService.updateProject(projectPo);

        userProjectService.deleteProjectUser(id);
        String memberUid = body.getMemberUid();
        if(null != memberUid){
            List<UserProjectPo> list = generateUserProjectPoList(memberUid, body.getUid(), id);
            userProjectService.saveBatch(list);
        }
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
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
}
