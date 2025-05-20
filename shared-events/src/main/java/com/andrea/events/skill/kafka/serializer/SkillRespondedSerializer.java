package com.andrea.events.skill.kafka.serializer;

import com.andrea.events.skill.SkillResponded;

import io.quarkus.kafka.client.serialization.ObjectMapperSerializer;

public class SkillRespondedSerializer extends ObjectMapperSerializer<SkillResponded> {
    public SkillRespondedSerializer() {
        super();
    }
}
