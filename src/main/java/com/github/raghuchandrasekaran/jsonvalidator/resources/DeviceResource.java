package com.github.raghuchandrasekaran.jsonvalidator.resources;

import com.github.raghuchandrasekaran.jsonvalidator.model.Device;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Rest API to access Device
 */
@Path("device")
public class DeviceResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Device getDevice() {
        Device device = new Device();
        device.setName("Test");
        return device;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Device addDevice(Device device) {
        device.setId(1);
        return device;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Device update(Device device) {
        device.setName("Updated");
        return device;
    }
}
