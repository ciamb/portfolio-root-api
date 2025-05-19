package com.andrea.portfolio.service.about;

import java.time.Duration;
import java.util.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.andrea.events.about.AboutRequested;
import com.andrea.events.about.AboutResponded;
import com.andrea.portfolio.service.broadcaster.EventLogBroadcaster;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AboutEventService {
    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    private final Emitter<AboutRequested> emitter;
    private final EventLogBroadcaster broadcaster;

    private final AboutTemplateService aboutTemplateService;

    @Inject
    public AboutEventService(
            @Channel("about-requested") Emitter<AboutRequested> emitter,
            EventLogBroadcaster broadcaster,
            AboutTemplateService aboutTemplateService) {
        this.emitter = emitter;
        this.broadcaster = broadcaster;
        this.aboutTemplateService = aboutTemplateService;
    }

    public Uni<AboutResponded> sendAndAwait(String requestId) {
        sendAboutRequested(requestId);

        return Uni.createFrom()
                .<AboutResponded>emitter(e -> aboutTemplateService
                        .onResponse(requestId, e::complete))
                .ifNoItem()
                .after(Duration.ofSeconds(5))
                .fail();
    }

    public void sendAboutRequested(String requestId) {
        log.info(() -> "[!] sending AboutRequested: %s".formatted(requestId));
        broadcaster.broadcast(requestId, "about.requested");
        emitter.send(new AboutRequested(requestId));
    }
}
