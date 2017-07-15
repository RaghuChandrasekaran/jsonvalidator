package com.github.raghuchandrasekaran.jsonvalidator.validators;

import com.github.raghuchandrasekaran.jsonvalidator.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * Validator for User Model
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class UserValidator extends Validator<User> {

    public UserValidator() {
        super(User.class, "user.json");
    }
}
