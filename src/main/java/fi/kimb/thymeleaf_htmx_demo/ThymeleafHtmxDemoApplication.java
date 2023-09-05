package fi.kimb.thymeleaf_htmx_demo;

import fi.kimb.thymeleaf_htmx_demo.entity.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafHtmxDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafHtmxDemoApplication.class, args);
    }

    @Autowired
    public void initialData(RuleRepository rr) {
        rr.save(new Rule(null, "Products", 0));
        rr.save(new Rule(null, "Features", 1));
        rr.save(new Rule(null, "Front page", 2));
    }
}
