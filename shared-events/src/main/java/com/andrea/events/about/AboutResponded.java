package com.andrea.events.about;

public record AboutResponded(
    String requestId,
    String name,
    String title,
    String bio,
    String location,
    String email,
    String website
) {

}
