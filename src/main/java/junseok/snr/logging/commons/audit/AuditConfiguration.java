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
        taskExecutor.setCorePoolSize(10);  // 기본 스레드
        taskExecutor.setMaxPoolSize(30);  // 최대 스레드 수 (큐가 꽉차면 증가)
        taskExecutor.setQueueCapacity(30);  // 30 개의 큐
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  // 쓰레드 풀과 큐가 꽉찬 상태에서 추가 요청 오면 발생. CallerRunsPolicy - taskExecutor 요청 쓰레드에서 처리
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true); // queue 작업 완료 시 까지 대기 후 셧 다운
        taskExecutor.setAwaitTerminationSeconds(60); // 최대 60초 까지만 대기 후 셧다운
        taskExecutor.setThreadNamePrefix("audit-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
