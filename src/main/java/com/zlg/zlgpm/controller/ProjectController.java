package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiBaseResp;
import com.zlg.zlgpm.controller.model.ApiCreateProjectRequest;
import com.zlg.zlgpm.controller.model.ApiProjectListResponse;
import com.zlg.zlgpm.controller.model.ApiUpdateProjectRequest;
import com.zlg.zlgpm.pojo.ProjectBo;
import com.zlg.zlgpm.pojo.ProjectPo;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.service.ProjectService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public ResponseEntity<ApiProjectListResponse> projectList(String name, Integer currentPage, Integer pageSize) {
        Page<ProjectBo> projectBoPage = projectService.projectList(name, currentPage, pageSize);
        ApiProjectListResponse apiProjectListResponse = dataConvertHelper.convert2projectListResponse(projectBoPage);
        return ResponseEntity.ok().body(apiProjectListResponse);
    }

    @Override
    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> updateProject(Integer id, ApiUpdateProjectRequest body) {
        ProjectPo projectPo = dataConvertHelper.convert2Project(body);
        projectPo.setId(id);
        projectService.updateProject(projectPo);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }
}
