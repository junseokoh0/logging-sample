package junseok.snr.logging.commons.audit;


public interface AuditLogger {

    public void debug(AuditLoggerVO auditLoggerVO);
    public void info(AuditLoggerVO auditLoggerVO);
    public void error(AuditLoggerVO auditLoggerVO);
}