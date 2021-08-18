package junseok.snr.logging.commons.audit;

import lombok.*;

@EqualsAndHashCode
@Getter @Setter
public class AuditLoggerVO {
    private String uid;
    private final String ip;
    private final String className;
    private final String topMenuName;
    private final String subMenuName;
    private final Object preData;
    private final Object postData;
    private final String message;

    public static AuditLoggerBuilder builder() {
        return new AuditLoggerBuilder();
    }

    public AuditLoggerVO(String ip, String className, String topMenuName, String subMenuName, Object preData, Object postData, String message) {
        this.ip = ip;
        this.className = className;
        this.topMenuName = topMenuName;
        this.subMenuName = subMenuName;
        this.preData = preData;
        this.postData = postData;
        this.message = message;
    }

    public static class AuditLoggerBuilder {
        private String ip;
        private String className;
        private String topMenuName;
        private String subMenuName;
        private Object preData;
        private Object postData;
        private String message;

        public AuditLoggerBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public AuditLoggerBuilder className(String className) {
            this.className = className;
            return this;
        }

        public AuditLoggerBuilder topMenuName(String topMenuName) {
            this.topMenuName = topMenuName;
            return this;
        }

        public AuditLoggerBuilder subMenuName(String subMenuName) {
            this.subMenuName = subMenuName;
            return this;
        }

        public AuditLoggerBuilder preData(Object preData) {
            this.preData = preData;
            return this;
        }

        public AuditLoggerBuilder postData(Object postData) {
            this.postData = postData;
            return this;
        }

        public AuditLoggerBuilder message(String message) {
            this.message = message;
            return this;
        }

        public AuditLoggerVO build() {
            return new AuditLoggerVO(this.ip, this.className, this.topMenuName, this.subMenuName, this.preData, this.postData, this.message);
        }
    }
}
