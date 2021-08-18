package junseok.snr.logging.commons.back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Slf4j
public class AsyncConfig {

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setRejectedExecutionHandler((r, executor) -> {
            log.error("RejectedExecutionHandler apply");
        });
        taskExecutor.setAwaitTerminationSeconds(10);
        return taskExecutor;
    }
}
