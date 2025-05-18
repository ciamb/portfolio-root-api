package com.andrea.portfolio.service;

import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.andrea.events.about.AboutResponded;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AboutRespondedListener {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    @Incoming("about-responded")
    public AboutResponded consumeAboutResponse(AboutResponded response) {
        log.info(() -> "[!] received AboutResponded: %s".formatted(response));
        return response;
    }
}
