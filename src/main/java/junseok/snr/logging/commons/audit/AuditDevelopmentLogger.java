package junseok.snr.logging.commons.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@Slf4j
@Component
public class AuditDevelopmentLogger implements AuditLogger {

    private final ObjectMapper objectMapper;

    public AuditDevelopmentLogger(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void debug(AuditLoggerVO auditLoggerVO) {
        try {
            auditLoggerVO.setUid(UUID.randomUUID().toString());
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.debug(objectMapper.writeValueAsString(auditLoggerVO));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            log.error("AuditLogger JsonProcessingException", exception);
        }
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void info(AuditLoggerVO auditLoggerVO) {
        try {
            auditLoggerVO.setUid(UUID.randomUUID().toString());
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.info(objectMapper.writeValueAsString(auditLoggerVO));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            log.error("AuditLogger JsonProcessingException", exception);
        }
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void error(AuditLoggerVO auditLoggerVO) {
        try {
            auditLoggerVO.setUid(UUID.randomUUID().toString());
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.error(objectMapper.writeValueAsString(auditLoggerVO));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            log.error("AuditLogger JsonProcessingException", exception);
        }
    }
}