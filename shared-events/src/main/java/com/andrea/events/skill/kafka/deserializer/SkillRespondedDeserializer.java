package com.andrea.events.skill.kafka.deserializer;

import com.andrea.events.skill.SkillResponded;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class SkillRespondedDeserializer extends ObjectMapperDeserializer<SkillResponded> {

    public SkillRespondedDeserializer() {
        super(SkillResponded.class);
    }

}
