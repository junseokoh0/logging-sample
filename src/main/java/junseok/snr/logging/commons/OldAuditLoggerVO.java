package junseok.snr.logging.commons;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OldAuditLoggerVO {
    private String uid;
    private String ip;
    private String topMenuName;
    private String subMenuName;
    private Object preData;
    private Object postData;
}
