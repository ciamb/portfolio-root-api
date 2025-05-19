package com.andrea.portfolio.rest;

import com.andrea.portfolio.service.broadcaster.EventLogBroadcaster;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/event-log")
public class EventLogResource {

    private final EventLogBroadcaster broadcaster;
    private final Template eventLog;

    @Inject
    public EventLogResource(
        EventLogBroadcaster broadcaster,
        @Location("event-log.qute.html") Template eventLog) {
        this.broadcaster = broadcaster;
        this.eventLog = eventLog;
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> stream() {
        return broadcaster.stream();
    }

    @GET
    @Path("/ui")
    @Produces(value = {MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    public TemplateInstance ui() {
        return eventLog.instance();
    }
}
