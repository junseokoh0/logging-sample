package junseok.snr.logging.controller;

import junseok.snr.logging.service.ExecuteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/treadexecute")
@Slf4j
public class ThreadExecuteController {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final ExecuteService executeService;

    public ThreadExecuteController(ThreadPoolTaskExecutor threadPoolTaskExecutor, ExecuteService executeService) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.executeService = executeService;
    }

    @GetMapping("/run/{count}")
    public String run(@PathVariable int count) {

        Runnable r = () -> {
            try {
                Thread.sleep(3000);
                log.debug("Thread Name ::: {}", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < count; i++) {
            threadPoolTaskExecutor.execute(r);
        }
        return "";
    }


    @GetMapping("/run-ano/{count}")
    public String runAno(@PathVariable int count) {

        for (int i = 0; i < count; i++) {
            executeService.task();
        }

        return "";
    }



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
