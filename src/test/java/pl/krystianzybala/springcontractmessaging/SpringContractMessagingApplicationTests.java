package pl.krystianzybala.springcontractmessaging;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "pl.krystianzybala:contract", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class SpringContractMessagingApplicationTests {

    @Autowired
    private FraudListener fraudListener;

    @Autowired
    private StubTrigger stubTrigger;

    @Test
    public void testConsumeMessage() {

        stubTrigger.trigger("triggerFraud");
        BDDAssertions.then(this.fraudListener.message).isEqualTo("Krystian send message!");
    }

}
