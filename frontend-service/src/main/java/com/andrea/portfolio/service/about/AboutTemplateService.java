package com.andrea.portfolio.service.about;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.logging.Logger;

import com.andrea.events.about.AboutResponded;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AboutTemplateService {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    private final Map<String, Consumer<AboutResponded>> waiters = new ConcurrentHashMap<>();

    public void onResponse(String requestId, Consumer<AboutResponded> handler) {
        waiters.put(requestId, handler);
    }

    public void setAboutResponded(AboutResponded response) {
        log.info(() -> "received template to render: %s".formatted(response));
        Consumer<AboutResponded> waiter = waiters.remove(response.requestId());
        if (waiter != null) {
            waiter.accept(response);
        }
    }

}
