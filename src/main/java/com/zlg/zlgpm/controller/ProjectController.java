package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.bo.ProjectStatisticsBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.service.ProjectService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Override
    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> createProject(ApiCreateProjectRequest body) {
        projectService.createProject(body);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> deleteProject(Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    public ResponseEntity<List<String>> getProjectVersions(String projectName) {
        List<Map<String, String>> projectVersions = projectService.getProjectVersions(projectName);
        List<String> responses = dataConvertHelper.convert2List(projectVersions, "version");
        return ResponseEntity.ok(responses);
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
    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> updateProject(Integer id, ApiUpdateProjectRequest body) {
        ProjectPo projectPo = dataConvertHelper.convert2ProjectPo(body);
        projectPo.setId(id);
        projectService.updateProject(projectPo);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }
}
