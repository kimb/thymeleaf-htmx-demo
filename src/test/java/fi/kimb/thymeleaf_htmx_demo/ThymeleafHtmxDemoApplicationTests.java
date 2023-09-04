package fi.kimb.thymeleaf_htmx_demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThymeleafHtmxDemoApplicationTests {
    @Autowired
    RuleRepository repository;

    @Test
    void contextLoads() {
    }

}
