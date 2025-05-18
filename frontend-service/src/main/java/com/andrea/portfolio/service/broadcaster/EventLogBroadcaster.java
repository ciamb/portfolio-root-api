package com.andrea.portfolio.service.broadcaster;

import java.util.LinkedList;
import java.util.Queue;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventLogBroadcaster {

    private final Queue<BroadcastProcessor<String>> listeners = new LinkedList<>();

    public void broadcast(Object event, String topic) {
        var msg = "[%s] %s".formatted(topic, event.toString());
        listeners.forEach(l -> l.onNext(msg));
    }

    public Multi<String> stream() {
        var processor = BroadcastProcessor.<String>create();
        listeners.add(processor);
        return processor;
    }
}
