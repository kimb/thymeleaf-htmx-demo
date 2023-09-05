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
import java.util.Optional;

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

    @PostMapping(path = "/order")
    @Transactional
    public String saveOrder(@RequestParam Long[] id, final Model model) {
        LOG.error("Saving rule order as: " + Arrays.asList(id));
        for (int i = 0; i < id.length; i++) {
            Long ruleId = id[i];
            Optional<Rule> rule = ruleRepository.findById(ruleId);
            if (rule.isEmpty()) {
                throw new IllegalArgumentException("No rule by id=" + ruleId);
            }
            rule.get().priority = i;
            LOG.info("Saving " + rule.get());
            ruleRepository.save(rule.get());
        }
        return list(model) + " :: rules";
    }
}
