package com.example.Exception;

import jakarta.ws.rs.ext.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

    public Response toResponse(NotFoundException ex) {
        return Response.status(404)
                .entity("{\"error\":\"" + ex.getMessage() + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
