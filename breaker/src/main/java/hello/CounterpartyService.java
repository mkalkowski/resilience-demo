package hello;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class CounterpartyService {

  private final RestTemplate restTemplate;

  public CounterpartyService(RestTemplate rest) {
    this.restTemplate = rest;
  }

  @HystrixCommand(fallbackMethod = "cachedCpty")
  public String fetchCpty() {
    URI uri = URI.create("http://localhost:8090/counterparty");

      return this.restTemplate.getForObject(uri, String.class);
  }

  public String cachedCpty() {
    return "CACHED_CPTY" + System.lineSeparator();
  }

}
