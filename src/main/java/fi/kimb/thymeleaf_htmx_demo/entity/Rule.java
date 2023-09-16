package fi.kimb.thymeleaf_htmx_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rule {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public Integer priority;
}
