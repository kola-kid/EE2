package com.accenture.flowershop.be.business.marshalling;

import com.accenture.flowershop.be.entity.user.User;

import java.io.IOException;

public interface UserMarshgallingService {

    void convertFromObjectToXML(User user, String filepath) throws IOException;

    User convertFromXMLToObject(String filepath) throws IOException;
}
