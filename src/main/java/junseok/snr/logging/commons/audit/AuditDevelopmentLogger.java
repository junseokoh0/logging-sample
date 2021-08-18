package junseok.snr.logging.commons.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@Profile({"dev", "test"})
public class AuditDevelopmentLogger implements AuditLogger {

    private final ObjectMapper objectMapper;

    public AuditDevelopmentLogger(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void debug(AuditItem auditItem) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.debug(objectMapper.writeValueAsString(auditItem));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            log.error("AuditLogger JsonProcessingException", exception);
        }
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void info(AuditItem auditItem) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.info(objectMapper.writeValueAsString(auditItem));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            log.error("AuditLogger JsonProcessingException", exception);
        }
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void error(AuditItem auditItem) {
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.error(objectMapper.writeValueAsString(auditItem));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
            log.error("AuditLogger JsonProcessingException", exception);
        }
    }
}