package com.andrea.portfolio.service;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

import com.andrea.portfolio.model.About;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AboutService {

    public About getAbout() {
        Yaml yaml = new Yaml();
        InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("data/about.yaml");
        return yaml.loadAs(is, About.class);
    }
}
