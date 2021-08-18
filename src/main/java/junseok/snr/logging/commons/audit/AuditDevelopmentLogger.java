package junseok.snr.logging.commons.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class AuditDevelopmentLogger implements AuditLogger {

    private final ObjectMapper objectMapper;

    public AuditDevelopmentLogger(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void log(AuditLoggerVO auditLoggerVO) {
        try {
            auditLoggerVO.setUid(UUID.randomUUID().toString());
            auditLoggerVO.setIp(getClientIP());
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.info(objectMapper.writeValueAsString(auditLoggerVO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String getClientIP() {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        if (StringUtils.isNotEmpty(httpServletRequest.getHeader("X-FORWARD-FOR"))) {
            return httpServletRequest.getHeader("X-FORWARD-FOR");
        }
        return httpServletRequest.getRemoteAddr();
    }
}