package com.andrea.portfolio.service;

import java.io.InputStream;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import com.andrea.portfolio.model.Skill;
import com.andrea.portfolio.model.SkillsWrapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillsService {

    public List<Skill> getSkills() {
        Yaml yaml = new Yaml();
        InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("data/skills.yaml");
        SkillsWrapper wrapper = yaml.loadAs(is, SkillsWrapper.class);
        return wrapper.getSkills();
    }

}
