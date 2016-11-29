package bulkhead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StaticDataController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/static/{id}")
    @Cacheable(cacheNames = "static-data")
    public String loadData(@PathVariable String id) throws InterruptedException {

        log.info("Loading static data...");
        Thread.sleep(1000L);
        return "Finished request " + id + System.lineSeparator();
    }
}