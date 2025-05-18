package com.andrea.portfolio.service;

import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.andrea.events.about.AboutRequested;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AboutEventService {
    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    @Channel("about-requested")
    Emitter<AboutRequested> emitter;

    public void sendAboutRequested(String requestId) {
        log.info(() -> "[!] sending AboutRequested: %s".formatted(requestId));
        emitter.send(new AboutRequested(requestId));
    }
}
