package bulkhead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class BulkheadController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CountingService countingService;

    @RequestMapping("/countTo/{count}")
    @ResponseBody
    String countTo(@PathVariable Long count) throws InterruptedException {
        log.info("submitting task... ");
        Future<String> fResult = countingService.countTo(count);

        try {
            return fResult.get();
        } catch (ExecutionException e) {
            log.error("Error executing ", e);
        }
        return "error";
    }

    @RequestMapping("/print/{count}")
    @ResponseBody
    String print(@PathVariable Long count) throws InterruptedException {
        log.info("submitting task... ");
        Future<String> fResult = countingService.countTo(count);

        try {
            return fResult.get();
        } catch (ExecutionException e) {
            log.error("Error executing ", e);
        }
        return "error";
    }
}
