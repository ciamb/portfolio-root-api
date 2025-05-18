package com.andrea.portfolio.service.about;

import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.andrea.events.about.AboutResponded;
import com.andrea.portfolio.service.broadcaster.EventLogBroadcaster;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AboutRespondedListener {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());
    
    private final EventLogBroadcaster broadcaster;

    @Inject
    public AboutRespondedListener(EventLogBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    @Incoming("about-responded")
    public AboutResponded consumeAboutResponse(AboutResponded response) {
        log.info(() -> "[!] received AboutResponded: %s".formatted(response));
        broadcaster.broadcast(response, "about.responded");
        return response;
    }
}
