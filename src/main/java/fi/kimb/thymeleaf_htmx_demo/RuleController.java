package fi.kimb.thymeleaf_htmx_demo;

import fi.kimb.thymeleaf_htmx_demo.entity.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/")
public class RuleController {
    private static Logger LOG = LoggerFactory.getLogger(RuleController.class);
    @Autowired
    RuleRepository ruleRepository;

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("rules", ruleRepository.findAllByOrderByPriority());
        return "ruleList";
    }

    @PostMapping("/priority")
    @Transactional
    public String savePriorities(@RequestParam(name = "id") Rule[] rules, final Model model) {
        for (int i = 0; i < rules.length; i++) {
            rules[i].priority = i;
        }
        ruleRepository.saveAll(Arrays.asList(rules));
        return list(model) + " :: rules";
    }

    @GetMapping("/rule/{id}")
    public String rule(final Model model, @PathVariable("id") long id) {
        model.addAttribute("rule", ruleRepository.findById(id).get());
        return "rule";
    }

    @PostMapping("/rule")
    @Transactional
    public String saveRule(Rule rule, final Model model) {
        LOG.warn("Saving " + rule);
        LOG.info("Model" + model);
        ruleRepository.save(rule);
        return list(model);
    }

    @GetMapping("/new")
    public String newRule(final Model model) {
        Rule rule = new Rule();
        rule.priority = ruleRepository.getMaxPriority() + 1;
        model.addAttribute("rule", rule);
        return "rule";
    }
}
