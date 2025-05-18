package com.andrea.events.about.kafka.deserializer;

import com.andrea.events.about.AboutRequested;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class AboutRequestedDeserializer extends ObjectMapperDeserializer<AboutRequested> {
    public AboutRequestedDeserializer() {
        super(AboutRequested.class);
    }
}
