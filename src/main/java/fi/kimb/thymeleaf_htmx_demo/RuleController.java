package fi.kimb.thymeleaf_htmx_demo;

import fi.kimb.thymeleaf_htmx_demo.entity.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping(path = "/priority")
    @Transactional
    public String savePriorities(@RequestParam(name = "id") Rule[] rules, final Model model) {
        for (int i = 0; i < rules.length; i++) {
            rules[i].priority = i;
        }
        ruleRepository.saveAll(Arrays.asList(rules));
        return list(model) + " :: rules";
    }
}
