package com.andrea.events.about.kafka.serializer;

import com.andrea.events.about.AboutResponded;

import io.quarkus.kafka.client.serialization.ObjectMapperSerializer;

public class AboutRespondedSerializer extends ObjectMapperSerializer<AboutResponded> {
    public AboutRespondedSerializer() {
        super();
    }
}
