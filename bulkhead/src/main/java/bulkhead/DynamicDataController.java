package bulkhead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicDataController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/dynamic/{id}")
    @Cacheable(cacheNames = "dynamic-data")
    public String loadData(@PathVariable String id) throws InterruptedException {

        log.info("Loading dynamic data for {}...", id);
        Thread.sleep(1100L);
        return "Finished request " + id + System.lineSeparator();
    }
}