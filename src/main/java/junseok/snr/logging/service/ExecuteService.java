package junseok.snr.logging.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExecuteService {

    @Async("asyncAnnotationThreadPoolTaskExecutor")
    public void task() {
        try {
            Thread.sleep(3000);
            log.debug("Thread Name ::: {}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
