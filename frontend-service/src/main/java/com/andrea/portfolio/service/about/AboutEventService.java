package com.andrea.portfolio.service.about;

import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.andrea.events.about.AboutRequested;
import com.andrea.portfolio.service.broadcaster.EventLogBroadcaster;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AboutEventService {
    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    private final Emitter<AboutRequested> emitter;
    private final EventLogBroadcaster broadcaster;

    @Inject
    public AboutEventService(
            @Channel("about-requested") Emitter<AboutRequested> emitter,
            EventLogBroadcaster broadcaster) {
        this.emitter = emitter;
        this.broadcaster = broadcaster;
    }

    public void sendAboutRequested(String requestId) {
        log.info(() -> "[!] sending AboutRequested: %s".formatted(requestId));
        broadcaster.broadcast(requestId, "about.requested");
        emitter.send(new AboutRequested(requestId));
    }
}
