package application.app;

import infrastructure.events.FeedingTimeEvent;
import presentation.controllers.AnimalsController;

public class Main {

    private static Zoo zoo;
    private static CommandsHandler commandsHandler = new CommandsHandler();
    private static AnimalsController controller;

    public static void main(String[] args) {
        System.out.print("enter health criteria for zoo");
        zoo = new Zoo(commandsHandler.readCriteria());
//        commandsHandler.handle(zoo);
        FeedingTimeEvent feedingTimeEvent = new FeedingTimeEvent();
        feedingTimeEvent.start();

        controller = new AnimalsController(zoo);

    }
}
