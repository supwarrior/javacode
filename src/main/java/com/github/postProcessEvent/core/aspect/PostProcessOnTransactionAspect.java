package com.github.postProcessEvent.core.aspect;

import com.github.common.cons.Response;
import com.github.ddd.SpringContextUtil;
import com.github.mycim.dto.Infos;
import com.github.mycim.dto.TaskContextHolder;
import com.github.postProcessEvent.core.EnablePostProcess;
import com.github.postProcessEvent.core.event.PostProcessEvent;
import com.github.postProcessEvent.method.IPostProcessTaskMethod;
import com.github.postProcessEvent.model.ExecutePhase;
import com.github.postProcessEvent.model.PostProcessParam;
import com.github.postProcessEvent.model.PostProcessTask;
import com.github.postProcessEvent.model.PostProcessTaskPlan;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class PostProcessOnTransactionAspect {

    @Autowired
    @Qualifier("defaultProxy")
    private DefaultPostProcessProxy defaultProxy;

    @Autowired
    private  ApplicationEventPublisher eventPublisher;

    @Autowired
    private  IPostProcessTaskMethod taskMethod;

    @Pointcut("@annotation(com.github.postProcessEvent.core.EnablePostProcess)")
    public void isPostProcessEnabled() {}

    @AfterReturning(value = "isPostProcessEnabled()", returning = "result")
    public void registerTaskOnSuccess(JoinPoint joinPoint, Object result) {
        Object out;
        if (result instanceof Response) {
            out =  ((Response) result).getBody();
        } else {
            out = result;
        }
        Infos.ObjCommon objCommon = TaskContextHolder.getObjCommon();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<? extends PostProcessPlanProxy> proxyType = signature.getMethod().getAnnotation(EnablePostProcess.class).proxy();
        PostProcessPlanProxy planProxy = proxyType == PostProcessPlanProxy.class ? defaultProxy : SpringContextUtil.getSingletonBean(proxyType);
        // 组装返回参数和入参
        PostProcessParam.PlanTask planTask = new PostProcessParam.PlanTask(objCommon, joinPoint.getArgs()[0], out);
        PostProcessTaskPlan.generateTaskId((objCommon.getUser().getUserID().getReferenceKey()));
        List<PostProcessTask> plannedTask = planProxy.plan(planTask);

        PostProcessParam.Register register = new PostProcessParam.Register();
        register.setPostTasks(plannedTask);
        register.setTaskId(plannedTask.stream().findFirst().map(PostProcessTask::getTaskId).orElse(""));
        PostProcessTaskPlan.clearTaskId();

        PostProcessEvent chainedEvent = PostProcessEvent.syncEvent(this, ExecutePhase.CHAINED);
        chainedEvent.setTaskId(register.getTaskId());
        chainedEvent.setObjCommon(objCommon);
        chainedEvent.setTasks(register.getPostTasks());

        eventPublisher.publishEvent(chainedEvent);
        this.doCreateTask(objCommon, register);

    }
    private void doCreateTask(Infos.ObjCommon objCommon, PostProcessParam.Register register) {
        List<PostProcessTask> postTasks = register.getPostTasks().stream().collect(Collectors.toList());
        taskMethod.createTaskRecords(objCommon, postTasks);
    }

}
