package com.sparta.limited.common_module.common;

import com.sparta.limited.common_module.common.aop.RoleCheckAspect;
import com.sparta.limited.common_module.config.AuditorAwareConfig;
import com.sparta.limited.common_module.config.PageableConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Import({
    AuditorAwareConfig.class,
    RoleCheckAspect.class,
    PageableConfig.class
})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableCommonModule {

}
