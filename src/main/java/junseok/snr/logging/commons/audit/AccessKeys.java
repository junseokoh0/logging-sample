package junseok.snr.logging.commons.audit;

import lombok.Getter;
import java.beans.ConstructorProperties;

@Getter
public class AccessKeys {
    public static final String ACCESS_KEY_ID = "accessKeyId";
    public static final String SECRET_KEY = "secretKey";
    private final String accessKeyIdValue;
    private final String secretKeyValue;

    @ConstructorProperties({"accessKeyId", "secretKey"})
    public AccessKeys(final String accessKeyIdValue, final String secretKeyValue) {
        this.accessKeyIdValue = accessKeyIdValue;
        this.secretKeyValue = secretKeyValue;
    }
}
