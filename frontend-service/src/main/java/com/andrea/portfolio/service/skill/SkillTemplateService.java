package com.andrea.portfolio.service.skill;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.logging.Logger;

import com.andrea.events.skill.SkillResponded;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SkillTemplateService {

    private final Logger log = Logger.getLogger(getClass().getSimpleName());

    private final Map<String, Consumer<SkillResponded>> waiters = new ConcurrentHashMap<>();

    public void onResponde(String requestId, Consumer<SkillResponded> handler) {
        waiters.put(requestId, handler);
    }

    public void setSkillResponded(SkillResponded response) {
        log.info(() -> "received template to render: %s".formatted(response));
        Consumer<SkillResponded> waiter = waiters.remove(response.requestId());
        if (waiter != null) {
            waiter.accept(response);
        }
    }

}
