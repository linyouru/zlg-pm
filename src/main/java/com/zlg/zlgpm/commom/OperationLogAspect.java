package com.zlg.zlgpm.commom;

import com.zlg.zlgpm.pojo.po.OperationLogPo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
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
        if(null==currentUser){
            return;
        }
        operationLogPo.setUserName(currentUser.getUserName());
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
            switch (type){
                case "User":
                    UserPo userPo = (UserPo) returnValue;
                    sb.append(": ").append(userPo.getUserName());
                    break;
                case "Project":
                    ProjectPo projectPo = (ProjectPo) returnValue;
                    sb.append(": ").append(projectPo.getName());
                    break;
                case "Task":
                    TaskPo taskPo = (TaskPo) returnValue;
                    sb.append(": ").append(taskPo.getTask());
                    break;
                default:
                    break;
            }
        }
        operationLogPo.setRecord(sb.toString());
        logService.createOperationLog(operationLogPo);

    }

}
