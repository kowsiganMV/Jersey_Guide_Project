package com.example.Filter;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.ext.Provider;

@Provider
@PreMatching
public class RequestLogger implements ContainerRequestFilter {

    public void filter(ContainerRequestContext req) {
        System.out.println("Incoming Request: " +
                req.getMethod() + " " +
                req.getUriInfo().getRequestUri());
    }
}
