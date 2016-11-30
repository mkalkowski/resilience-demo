package counterparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CounterpartyApplication  {

  @RequestMapping(value = "/counterparty")
  public String counterparty(){
    return "C12345";
  }

  public static void main(String[] args) {
    SpringApplication.run(CounterpartyApplication.class, args);
  }
}
