package mall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import mall.NotifyApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { NotifyApplication.class })
public class CucumberSpingConfiguration {}
