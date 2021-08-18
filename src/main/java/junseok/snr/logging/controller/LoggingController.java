package junseok.snr.logging.controller;

import io.micrometer.core.instrument.util.StringUtils;
import junseok.snr.logging.commons.audit.AuditLogger;
import junseok.snr.logging.commons.audit.AuditLoggerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/logging")
@Slf4j
@RequiredArgsConstructor
public class LoggingController {

    private final AuditLogger logger;

    @GetMapping("/{message}")
    public String message(@PathVariable String message) {

        logger.log(AuditLoggerVO.builder()
                .ip(getClientIP())
                .className(LoggingController.class.getSimpleName())
                .topMenuName("상위메뉴")
                .subMenuName("서브메뉴")
                .preData(message)
                .build());
        return message;
    }

    @GetMapping("/thrownException")
    public String thrownException(@PathVariable String message) {

        return message;
    }

    private String getClientIP() {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        if (StringUtils.isNotEmpty(httpServletRequest.getHeader("X-FORWARD-FOR"))) {
            return httpServletRequest.getHeader("X-FORWARD-FOR");
        }
        return httpServletRequest.getRemoteAddr();
    }

}
