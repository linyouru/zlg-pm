package com.zlg.zlgpm.commom;

import com.zlg.zlgpm.controller.model.ApiUserLoginResponse;
import com.zlg.zlgpm.pojo.po.*;
import com.zlg.zlgpm.service.OperationLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
public class OperationLogAspect {

    private static final String TASK_END = "1";
    @Resource
    private OperationLogService logService;

    //指定切入点为自定义注解OperationLog
    @Pointcut("@annotation(com.zlg.zlgpm.commom.OperationLog)")
    public void pointcut() {
    }


    @AfterReturning(value = "pointcut()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        OperationLogPo operationLogPo = new OperationLogPo();
        UserPo currentUser = (UserPo) SecurityUtils.getSubject().getPrincipal();
        if (null == currentUser) {
            return;
        }
        operationLogPo.setUserName(currentUser.getNickName());
        operationLogPo.setUid(Math.toIntExact(currentUser.getId()));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = signature.getName();
        OperationLog annotation = method.getAnnotation(OperationLog.class);
        StringBuilder sb = new StringBuilder();
        if (annotation != null) {
            String value = annotation.value();
            String type = annotation.type();
            sb.append(value);
            switch (type) {
                case "User":
                    ApiUserLoginResponse userPo = (ApiUserLoginResponse) returnValue;
                    sb.append(": ").append(userPo.getUserName());
                    break;
                case "Project":
                    ProjectPo projectPo = (ProjectPo) returnValue;
                    sb.append(": ").append(projectPo.getName());
                    break;
                case "Task":
                    TaskPo taskPo = (TaskPo) returnValue;
                    //判断任务状态,若为已完成则定制化日志描述
                    String status = taskPo.getStatus();
                    if (TASK_END.equals(status)) {
                        sb.delete(0, sb.length());
                        sb.append("验收任务");
                    }
                    sb.append(": ").append(taskPo.getTask());
                    break;
                case "Feedback":
                    TaskFeedbackPo taskFeedbackPo = (TaskFeedbackPo) returnValue;
                    sb.append(": ").append(taskFeedbackPo.getFeedback());
                    break;
                default:
                    break;
            }
        }
        operationLogPo.setRecord(sb.toString());
        logService.createOperationLog(operationLogPo);

    }

}
