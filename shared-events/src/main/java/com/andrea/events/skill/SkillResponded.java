package com.andrea.events.skill;

import java.util.List;

public record SkillResponded(
    String requestId,
    List<String> skills
) {
}
