package junseok.snr.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junseok.snr.logging.commons.audit.AccessKeys;
import junseok.snr.logging.service.AuditSecretManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String secret = AuditSecretManager.getSecret();
        try {
            AccessKeys accessKeys = objectMapper.readValue(secret, AccessKeys.class);
            System.setProperty(AccessKeys.ACCESS_KEY_ID, accessKeys.getAccessKeyIdValue());
            System.setProperty(AccessKeys.SECRET_KEY, accessKeys.getSecretKeyValue());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SpringApplication.run(LoggingApplication.class, args);
    }
}
