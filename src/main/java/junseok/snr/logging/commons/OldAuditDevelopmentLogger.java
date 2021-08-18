package junseok.snr.logging.commons;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OldAuditDevelopmentLogger implements OldAuditLogger {

    private final ObjectMapper objectMapper;

    public OldAuditDevelopmentLogger(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Async("oldAuditLogThreadPoolTaskExecutor")
    @Override
    public void log(String uid, String ip) {
        OldAuditLoggerVO oldAuditLoggerVO = new OldAuditLoggerVO();
        oldAuditLoggerVO.setUid(uid);
        oldAuditLoggerVO.setIp(ip);
        logging(oldAuditLoggerVO);
    }

    @Override
    @Async("oldAuditLogThreadPoolTaskExecutor")
    public void log(String uid, String ip, String topMenuName) {
        OldAuditLoggerVO oldAuditLoggerVO = new OldAuditLoggerVO();
        oldAuditLoggerVO.setUid(uid);
        oldAuditLoggerVO.setIp(ip);
        oldAuditLoggerVO.setTopMenuName(topMenuName);
        logging(oldAuditLoggerVO);
    }

    @Override
    @Async("oldAuditLogThreadPoolTaskExecutor")
    public void log(String uid, String ip, String topMenuName, String subMenuName) {
        OldAuditLoggerVO oldAuditLoggerVO = new OldAuditLoggerVO();
        oldAuditLoggerVO.setUid(uid);
        oldAuditLoggerVO.setIp(ip);
        oldAuditLoggerVO.setTopMenuName(topMenuName);
        oldAuditLoggerVO.setSubMenuName(subMenuName);
        logging(oldAuditLoggerVO);
    }

    @Override
    @Async("oldAuditLogThreadPoolTaskExecutor")
    public void log(String uid, String ip, String topMenuName, String subMenuName, Object preData) {
        OldAuditLoggerVO oldAuditLoggerVO = new OldAuditLoggerVO();
        oldAuditLoggerVO.setUid(uid);
        oldAuditLoggerVO.setIp(ip);
        oldAuditLoggerVO.setTopMenuName(topMenuName);
        oldAuditLoggerVO.setSubMenuName(subMenuName);
        oldAuditLoggerVO.setPostData(preData);
        logging(oldAuditLoggerVO);
    }

    @Override
    @Async("oldAuditLogThreadPoolTaskExecutor")
    public void log(String uid, String ip, String topMenuName, String subMenuName, Object preData, Object postData) {
        OldAuditLoggerVO oldAuditLoggerVO = new OldAuditLoggerVO();
        oldAuditLoggerVO.setUid(uid);
        oldAuditLoggerVO.setIp(ip);
        oldAuditLoggerVO.setTopMenuName(topMenuName);
        oldAuditLoggerVO.setSubMenuName(subMenuName);
        oldAuditLoggerVO.setPostData(preData);
        oldAuditLoggerVO.setPostData(postData);
        logging(oldAuditLoggerVO);
    }

    private void logging(OldAuditLoggerVO oldAuditLoggerVO) {
        try {
            log.info(objectMapper.writeValueAsString(oldAuditLoggerVO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
