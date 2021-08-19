package junseok.snr.logging.commons.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
public class AuditConfiguration {

    @Bean(name = "auditLogThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor auditLogThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setQueueCapacity(30);
        taskExecutor.setAwaitTerminationSeconds(20);
//        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        taskExecutor.setRejectedExecutionHandler((r, executor) -> {
            log.error("RejectedExecutionHandler apply");
        });
        taskExecutor.setThreadNamePrefix("audit-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
