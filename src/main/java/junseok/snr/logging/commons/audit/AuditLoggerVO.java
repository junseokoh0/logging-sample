package junseok.snr.logging.commons.audit;

import lombok.*;

@EqualsAndHashCode
@Getter @Setter
public class AuditLoggerVO {
    private String uid;
    private String ip;
    private final String topMenuName;
    private final String subMenuName;
    private final Object preData;
    private final Object postData;

    public static AuditLoggerBuilder builder() {
        return new AuditLoggerBuilder();
    }

    public AuditLoggerVO(String topMenuName, String subMenuName, Object preData, Object postData) {
        this.topMenuName = topMenuName;
        this.subMenuName = subMenuName;
        this.preData = preData;
        this.postData = postData;
    }

    public static class AuditLoggerBuilder {
        private String topMenuName;
        private String subMenuName;
        private Object preData;
        private Object postData;

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

        public AuditLoggerVO build() {
            return new AuditLoggerVO(this.topMenuName, this.subMenuName, this.preData, this.postData);
        }
    }
}
