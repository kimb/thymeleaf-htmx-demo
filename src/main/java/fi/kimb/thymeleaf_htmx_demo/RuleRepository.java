package fi.kimb.thymeleaf_htmx_demo;

import fi.kimb.thymeleaf_htmx_demo.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findAllByOrderByPriority();
}
