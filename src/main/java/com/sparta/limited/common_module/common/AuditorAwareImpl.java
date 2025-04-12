package com.sparta.limited.common_module.common;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(getUserIdFromHeader());
    }

    public Long getUserIdFromHeader() {
        ServletRequestAttributes attributes =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String userId = request.getHeader("X-User-Id");

            if (userId != null && !userId.isBlank()) {
                try {
                    return Long.parseLong(userId);
                } catch (NumberFormatException e) {
                    return 0L;
                }
            }
        }

        return 0L;
    }
}