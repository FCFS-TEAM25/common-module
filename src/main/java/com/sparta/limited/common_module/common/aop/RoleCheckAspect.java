package com.sparta.limited.common_module.common.aop;

import com.sparta.limited.common_module.exception.BusinessException;
import com.sparta.limited.common_module.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class RoleCheckAspect {

    @Around("@annotation(roleCheck)")
    public Object checkRole(ProceedingJoinPoint joinPoint, RoleCheck roleCheck) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BusinessException(ErrorCode.RESOURCES_NOT_FOUND);
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String userRole = request.getHeader("X-User-Role");
        String whatRole = roleCheck.value();
        if (userRole == null || !userRole.equalsIgnoreCase(whatRole)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_ACCESS);
        }
        return joinPoint.proceed();
    }
}
