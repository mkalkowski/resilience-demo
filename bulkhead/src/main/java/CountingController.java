import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CountingController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/countTo/{count}")
    public String countTo(@PathVariable String count) throws InterruptedException {
        final long requestId = counter.incrementAndGet();

        log.info("{} Waiting for {} seconds ...", requestId, count );
        Thread.sleep(Long.parseLong(count) * 1000l);
        log.info("{} Done with request! ", requestId);


        return "Finished request " + requestId + System.lineSeparator();
    }
}