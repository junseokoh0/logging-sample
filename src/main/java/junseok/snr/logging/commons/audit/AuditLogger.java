package junseok.snr.logging.commons.audit;

public interface AuditLogger {
    void debug(AuditItem auditItem);
    void info(AuditItem auditItem);
    void error(AuditItem auditItem);
}