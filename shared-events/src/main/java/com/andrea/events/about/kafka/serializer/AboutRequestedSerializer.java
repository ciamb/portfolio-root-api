package com.andrea.events.about.kafka.serializer;

import com.andrea.events.about.AboutRequested;

import io.quarkus.kafka.client.serialization.ObjectMapperSerializer;

public class AboutRequestedSerializer extends ObjectMapperSerializer<AboutRequested> {
    public AboutRequestedSerializer() {
        super();
    }
}
