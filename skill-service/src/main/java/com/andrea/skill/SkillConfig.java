package com.andrea.skill;

import java.util.List;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "skills")
public interface SkillConfig {
    List<String> values();
}
