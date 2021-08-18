package junseok.snr.logging.controller;

import junseok.snr.logging.commons.audit.AuditLogger;
import junseok.snr.logging.commons.audit.AuditLoggerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logging")
@Slf4j
@RequiredArgsConstructor
public class LoggingController {

    private final AuditLogger logger;

    @GetMapping("/{message}")
    public String message(@PathVariable String message) {

        logger.log(AuditLoggerVO.builder()
                    .preData(message)
                    .build());
        return message;
    }
}
