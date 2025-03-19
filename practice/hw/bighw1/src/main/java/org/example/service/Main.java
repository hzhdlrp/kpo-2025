package org.example.service;

import org.example.facades.UserFacade;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
public class Main {

    public static void main(String[] args) {
        CommandsHandler handler = new CommandsHandler();
        UserFacade facade = new UserFacade();
        handler.handle(facade);
    }
}
