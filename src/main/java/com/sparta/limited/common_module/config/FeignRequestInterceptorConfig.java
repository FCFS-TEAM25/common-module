package com.sparta.limited.common_module.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignRequestInterceptorConfig {

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes requestAttributes
                    = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes == null) {
                return;
            }
            HttpServletRequest request = requestAttributes.getRequest();

            String[] headers = {"X-User-Id", "X-User-Role"};

            for (String header : headers) {
                String value = request.getHeader(header);
                if (value != null) {
                    requestTemplate.header(header, value);
                }
            }
        };
    }

}
