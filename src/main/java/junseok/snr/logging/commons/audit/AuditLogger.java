package junseok.snr.logging.commons.audit;


import java.util.concurrent.CompletableFuture;

public interface AuditLogger {

    public void log(AuditLoggerVO auditLoggerVO);
}