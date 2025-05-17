package com.andrea.about.service;

import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import com.andrea.events.about.AboutRequested;
import com.andrea.events.about.AboutResponded;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AboutProcessor {
    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    @Incoming("about-requested")
    @Outgoing("about-responded")
    public Uni<AboutResponded> onAboutRequested(AboutRequested event) {
        log.info(() -> "[!] received AboutRequested: %s".formatted(event));
        
        var res = new AboutResponded(
            event.requestId(),
            "Andrea",
            "Backend Developer",
            "Sviluppatore Java con 3 anni di esperienza",
            "Tarquinia, Italy",
            "andreaciambella@outlook.it",
            "https://github.com/ciamb"
        );
        
        return Uni.createFrom().item(res);
    }

}