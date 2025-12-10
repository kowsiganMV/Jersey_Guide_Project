package com.example.config;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        // Packages to scan
        packages("com.example.Resource");
        packages("com.example.Filter");
        packages("com.example.Exception");

        // Register everything explicitly (optional)
        register(org.glassfish.jersey.server.validation.ValidationFeature.class);
        register(org.glassfish.jersey.jackson.JacksonFeature.class);
    }
}
