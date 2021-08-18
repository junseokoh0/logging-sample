package junseok.snr.logging.commons;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class OldAuditConfiguration {

    @Bean(name = "oldAuditLogThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor oldAuditLogThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(30);
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setAwaitTerminationSeconds(20);
        taskExecutor.setThreadNamePrefix("audit-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
