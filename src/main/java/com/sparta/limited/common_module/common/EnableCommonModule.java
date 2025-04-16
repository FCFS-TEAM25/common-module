package com.sparta.limited.common_module.common;

import com.sparta.limited.common_module.common.aop.RoleCheckAspect;
import com.sparta.limited.common_module.config.AuditorAwareConfig;
import com.sparta.limited.common_module.config.FeignRequestInterceptorConfig;
import com.sparta.limited.common_module.config.PageableConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Import({
        AuditorAwareConfig.class,
        RoleCheckAspect.class,
        PageableConfig.class,
        FeignRequestInterceptorConfig.class
})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableCommonModule {

}
