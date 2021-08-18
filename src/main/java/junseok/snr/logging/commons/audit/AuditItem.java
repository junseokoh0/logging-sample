package junseok.snr.logging.commons.audit;

import lombok.*;

@EqualsAndHashCode
@Getter @Setter
public class AuditItem {
    private final String className;
    private final String topMenuName;
    private final String subMenuName;
    private final Object preData;
    private final Object postData;
    private final String message;

    public static AuditLoggerBuilder builder(String className) {
        return new AuditLoggerBuilder(className);
    }

    public AuditItem(String className, String topMenuName, String subMenuName, Object preData, Object postData, String message) {
        this.className = className;
        this.topMenuName = topMenuName;
        this.subMenuName = subMenuName;
        this.preData = preData;
        this.postData = postData;
        this.message = message;
    }

    public static class AuditLoggerBuilder {
        private String className;
        private String topMenuName;
        private String subMenuName;
        private Object preData;
        private Object postData;
        private String message;

        public AuditLoggerBuilder(String className) {
            this.className = className;
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

        public AuditItem build() {
            return new AuditItem(this.className, this.topMenuName, this.subMenuName, this.preData, this.postData, this.message);
        }
    }
}
