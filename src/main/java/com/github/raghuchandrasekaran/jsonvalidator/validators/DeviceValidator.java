package com.github.raghuchandrasekaran.jsonvalidator.validators;

import com.github.raghuchandrasekaran.jsonvalidator.model.Device;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

/**
 * Validator for Device Model
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceValidator extends Validator<Device> {

    public DeviceValidator() {
        super(Device.class, "device.json");
    }

}
