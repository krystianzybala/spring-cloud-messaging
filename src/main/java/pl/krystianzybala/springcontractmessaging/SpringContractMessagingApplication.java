package pl.krystianzybala.springcontractmessaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SpringContractMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringContractMessagingApplication.class, args);
    }
}

@Component
@Slf4j
class FraudListener {
    String message;

    @StreamListener(Sink.INPUT)
    public void onFraudMessage(FraudMessage  fraud) {

        log.info("Message: {}", fraud.getMessage());

        this.message = fraud.getMessage();
    }
}


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
class FraudMessage {
    private String message;
}
