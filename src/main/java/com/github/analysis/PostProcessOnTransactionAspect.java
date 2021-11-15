package com.github.analysis;

import com.github.ddd.SpringContextUtil;
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

@Slf4j
@Aspect
@Component("postProcessOnTransactionAspectAnalysis")
public class PostProcessOnTransactionAspect {

    private final ApplicationEventPublisher eventPublisher;
    private final DefaultPostProcessProxy defaultProxy;

    @Autowired
    public PostProcessOnTransactionAspect(ApplicationEventPublisher eventPublisher,
                                          @Qualifier("defaultPostProcessProxy") DefaultPostProcessProxy defaultPostProcessProxy) {
        this.eventPublisher = eventPublisher;
        this.defaultProxy = defaultPostProcessProxy;
    }


    @Pointcut("@annotation(com.github.analysis.EnablePostProcess)")
    public void isPostProcessEnabled() {
    }

    @AfterReturning(value = "isPostProcessEnabled()", returning = "result")
    public void registerTaskOnSuccess(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<? extends PostProcessPlanProxy> proxyType =
                signature.getMethod().getAnnotation(EnablePostProcess.class).proxy();
        PostProcessPlanProxy planProxy = proxyType == PostProcessPlanProxy.class ?
                defaultProxy : SpringContextUtil.getSingletonBean(proxyType);
        // 获取入参 和 返回结果
        Object[] args = joinPoint.getArgs();
        PostProcessPlanProxy.PlanTask planTask = new PostProcessPlanProxy.PlanTask(args,result);
        PostProcessEvent postProcessEvent = planProxy.getPostProcessEvent(planTask);
        // 发布事件
        eventPublisher.publishEvent(postProcessEvent);
    }
}
