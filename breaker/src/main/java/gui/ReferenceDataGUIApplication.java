package gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ReferenceDataGUIApplication {

  @Autowired
  private CounterpartyService counterpartyService;

  @Bean
  public RestTemplate rest(RestTemplateBuilder builder) {
    return builder.build();
  }

  @RequestMapping("/top/counterparty")
  public String topCpty() {
    return counterpartyService.fetchCpty();
  }

  public static void main(String[] args) {
    SpringApplication.run(ReferenceDataGUIApplication.class, args);
  }
}
