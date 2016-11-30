package bulkhead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CountingService {

    private AtomicInteger requestCount = new AtomicInteger(0);

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    public Future<String> countTo(Long count) {
        return taskExecutor.submit(new CountingTask(count));
    }

    class CountingTask implements Callable<String> {

        private final Long count;

        public CountingTask(Long count) {
            this.count = count;
        }

        @Override
        public String call() throws Exception {
            try {
                int requestId = requestCount.incrementAndGet();
                log.info("{} - sleeping... ", requestId);

                Thread.sleep(count * 1000);
                log.info("{} waking up! ", requestId);
                return String.format("Finished counting%s", requestId);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
        }
    }
}
