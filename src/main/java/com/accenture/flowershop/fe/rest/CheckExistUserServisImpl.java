package com.accenture.flowershop.fe.rest;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Component
@Path("/checkuser")
public class CheckExistUserServisImpl implements CheckExistUserServis {

    @Autowired
    UserBusinessService userBusinessService;

    public CheckExistUserServisImpl() {
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/check/{name}")
    @Override
    public boolean checkUserList(@PathParam("name") String username) {
        User user = userBusinessService.findUser(username);

        return !(user == null);
    }

}

