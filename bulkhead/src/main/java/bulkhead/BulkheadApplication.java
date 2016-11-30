package bulkhead;

import org.ehcache.core.EhcacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.core.task.TaskDecorator;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.cache.CacheManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableCaching
@EnableMBeanExport
public class BulkheadApplication {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private static final int QUEUE_SIZE = 3;
    private static final int POOL_SIZE = 3;

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor result = new ThreadPoolTaskExecutor();
        result.setCorePoolSize(POOL_SIZE);
        result.setMaxPoolSize(POOL_SIZE);
        result.setQueueCapacity(QUEUE_SIZE);
        result.setKeepAliveSeconds(0);
        result.setThreadNamePrefix("cmit-");
        result.setRejectedExecutionHandler((r, executor) -> {log.error("Rejecting new task!");});

        return result;
    }

    @Bean
    protected MBeanExporter myMbeanExporter() {
        MBeanExporter exporter = new MBeanExporter();
        Map<String,Object> beans = new HashMap<>();
        beans.put("com.nordea.cmit:type=executor,name=taskExecutor", taskExecutor());
        exporter.setBeans(beans);

        return exporter;
    }


    public static void main(String[] args) {
        SpringApplication.run(BulkheadApplication.class, args);
    }

}