package com.sparta.limited.common_module.common.resolver;

import com.sparta.limited.common_module.common.annotation.CurrentUserId;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserIdArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String HEADER_NAME = "X-User-Id";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUserId.class) && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String userIdHeader = request.getHeader(HEADER_NAME);

        if(userIdHeader == null) {
            throw new IllegalStateException("X-user-id 헤더가 존재하지 않습니다.");
        }
        try{
            return Long.parseLong(userIdHeader);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("X-User-id 헤더가 Long type 이 아닙니다.");
        }
    }
}
