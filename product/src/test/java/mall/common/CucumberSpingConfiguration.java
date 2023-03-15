package mall.common;

import io.cucumber.spring.CucumberContextConfiguration;
import mall.ProductApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { ProductApplication.class })
public class CucumberSpingConfiguration {}
