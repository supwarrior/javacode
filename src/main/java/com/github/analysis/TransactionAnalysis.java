package com.github.analysis;

import com.github.annotation.Transaction;
import com.github.common.cons.TransactionIDEnum;
import com.github.ddd.factory.BeanFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.lang.reflect.Method;


/**
 * 记录日志 需要在类上加入@Transaction注解
 *
 * @author 康盼Java开发工程师
 */
@Component
@Aspect
public class TransactionAnalysis {

    @Around(value = "execution(* com.github.ddd.controller..*.*(..))")
    public Object tracking(ProceedingJoinPoint joinPoint) throws Throwable {
        final Class<?> targetClass = joinPoint.getTarget().getClass();
        // 获取类的注解
        final boolean isTransaction = targetClass.isAnnotationPresent(Transaction.class);
        if (isTransaction) {
            StopWatch stopWatch = new StopWatch();
            // 获取方法上的注解
            final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            final Method targetMethod = targetClass.getMethod(signature.getName(), signature.getParameterTypes());
            final String methodName = signature.getName();
            final TransactionID transactionID = targetMethod.getAnnotation(TransactionID.class);
            if (transactionID != null) {
                TransactionIDEnum transactionIDEnum = transactionID.value();
                String transactionId = transactionIDEnum.getValue();
                stopWatch.start(targetClass.getName() + "." + methodName);
                // 执行方法
                joinPoint.proceed(joinPoint.getArgs());
                stopWatch.stop();
                LogDO logDO = new LogDO();
                logDO.setTransactionId(transactionId);
                logDO.setRequestTime(ThreadContextHolder.getRequestTime().toString());
                logDO.setMethodName(methodName);
                logDO.setTime(String.valueOf(stopWatch.getTotalTimeMillis()));
                Log log = BeanFactory.getDefaultBeanFactory().getBean(Log.class, new Object[]{logDO});
                log.saveLog();
            }
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }
}