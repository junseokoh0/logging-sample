package junseok.snr.logging.commons.audit;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junseok.snr.logging.service.AuditSecretManager;

public class AuditListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

    @Override
    public void start() {
        ObjectMapper objectMapper = new ObjectMapper();
        String secret = AuditSecretManager.getSecret();
        try {
            AccessKeys accessKeys = objectMapper.readValue(secret, AccessKeys.class);
            System.setProperty(AccessKeys.ACCESS_KEY_ID, accessKeys.getAccessKeyIdValue());
            System.setProperty(AccessKeys.SECRET_KEY, accessKeys.getSecretKeyValue());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext context) {
    }

    @Override
    public void onReset(LoggerContext context) {
    }

    @Override
    public void onStop(LoggerContext context) {
    }

    @Override
    public void onLevelChange(Logger logger, Level level) {
    }


    @Override
    public void stop() {
    }

    @Override
    public boolean isStarted() {
        return false;
    }
}