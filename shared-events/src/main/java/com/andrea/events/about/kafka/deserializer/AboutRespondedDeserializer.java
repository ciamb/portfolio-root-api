package com.andrea.events.about.kafka.deserializer;

import com.andrea.events.about.AboutResponded;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class AboutRespondedDeserializer extends ObjectMapperDeserializer<AboutResponded> {
    public AboutRespondedDeserializer() {
        super(AboutResponded.class);
    }
}
