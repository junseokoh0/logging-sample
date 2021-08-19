package junseok.snr.logging.commons.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("prod")
public class AuditProductLogger implements AuditLogger {

    private final ObjectMapper objectMapper;

    public AuditProductLogger(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void debug(AuditItem auditItem) {
        try {
            log.debug(objectMapper.writeValueAsString(auditItem));
        } catch (JsonProcessingException exception) {
            handleException(exception);
        }
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void info(AuditItem auditItem) {
        try {
            log.info(objectMapper.writeValueAsString(auditItem));
        } catch (JsonProcessingException exception) {
            handleException(exception);
        }
    }

    @Async("auditLogThreadPoolTaskExecutor")
    @Override
    public void error(AuditItem auditItem) {
        try {
            log.error(objectMapper.writeValueAsString(auditItem));
        } catch (JsonProcessingException exception) {
            handleException(exception);
        }
    }

    private void handleException(JsonProcessingException exception) {
        log.error("AuditLogger JsonProcessingException", exception);
    }
}