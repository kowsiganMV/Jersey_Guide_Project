package com.example.Filter;

import jakarta.ws.rs.container.*;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResponseHeaderFilter implements ContainerResponseFilter {

    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        res.getHeaders().add("Powered-By", "JerseyDemoApp");
    }
}
