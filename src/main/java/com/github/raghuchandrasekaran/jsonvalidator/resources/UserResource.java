package com.github.raghuchandrasekaran.jsonvalidator.resources;

import com.github.raghuchandrasekaran.jsonvalidator.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Rest API to access User
 */

@Path("user")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        User user = new User();
        user.setName("Test");
        return user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        user.setId(1);
        return user;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public User update(User user) {
        user.setName("Updated");
        return user;
    }
}
