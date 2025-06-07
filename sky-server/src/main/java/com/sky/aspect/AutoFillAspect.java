package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

/**
 * 自动填充切面
 * 用于处理自动填充注解标记的方法
 * 例如：在新增时自动填充创建时间、创建人等字段
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切点：匹配所有标记了 @AutoFill 注解的方法
     * 该注解用于标记需要自动填充的字段
     */
    @Pointcut("@annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointcut() {}

    @Before("autoFillPointcut()")
    public void beforeAutoFill(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("自动填充切面触发，执行前置处理");

        // 获取方法参数和操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.operationType();

        // 根据操作类型进行不同的处理
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            log.warn("自动填充切面未找到方法参数");
            return;
        }
        Object entity = args[0];

        LocalDateTime now = LocalDateTime.now();
        Long id = BaseContext.getCurrentId();

        if (operationType == OperationType.INSERT) {
            entity.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class).invoke(entity, now);
            entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, now);
            entity.getClass().getMethod(AutoFillConstant.SET_CREATE_USER, Long.class).invoke(entity, id);
            entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class).invoke(entity, id);
        } else if (operationType == OperationType.UPDATE) {
            entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, now);
            entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class).invoke(entity, id);
        }

    }
}
