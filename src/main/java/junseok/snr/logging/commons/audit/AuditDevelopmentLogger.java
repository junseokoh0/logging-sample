package junseok.snr.logging.commons.audit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
    public void log(AuditLoggerVO auditLoggerVO) {
        try {
            auditLoggerVO.setUid(UUID.randomUUID().toString());
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            log.info(objectMapper.writeValueAsString(auditLoggerVO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}