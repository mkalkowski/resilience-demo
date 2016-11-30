package bulkhead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PrintingService {

    private AtomicInteger requestCount = new AtomicInteger(0);

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    public Future<String> print(Long count) {
        return taskExecutor.submit(new PrintingTask());
    }

    class PrintingTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                int requestId = requestCount.incrementAndGet();
                log.info("{} - sleeping... ", requestId);

                Thread.sleep(2000);
                log.info("{} waking up! ", requestId);
                return String.format("Finished printing %s", requestId);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
        }
    }
}
