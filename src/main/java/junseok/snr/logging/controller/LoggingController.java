package junseok.snr.logging.controller;

import io.micrometer.core.instrument.util.StringUtils;
import junseok.snr.logging.commons.audit.AuditLogger;
import junseok.snr.logging.commons.audit.AuditItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/logging")
@RequiredArgsConstructor
public class LoggingController {

    private final AuditLogger logger;

    @GetMapping("/debug/{message}")
    public String debug(@PathVariable String message) {

        logger.debug(AuditItem.builder(LoggingController.class.getSimpleName())
                .preData(message)
                .postData(String.format("post-%s", message))
                .message("message 전송중 디버그 로깅!!!")
                .build());
        return message;
    }

    @GetMapping("/info/{message}")
    public String info(@PathVariable String message) {

        logger.info(AuditItem.builder(LoggingController.class.getSimpleName())
                .topMenuName("상위메뉴")
                .subMenuName("서브메뉴")
                .preData(message)
                .message("message 전송중 인포 로깅!!")
                .build());
        return message;
    }

    @GetMapping("/error/{message}")
    public String error(@PathVariable String message) {

        logger.error(AuditItem.builder(LoggingController.class.getSimpleName())
                .topMenuName("상위메뉴")
                .subMenuName("서브메뉴")
                .preData(message)
                .message("message 전송중 에러 로깅!!")
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
