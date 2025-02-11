package app;

import services.Zoo;

public class Main {

    private static Zoo zoo;
    private static CommandsHandler commandsHandler = new CommandsHandler();

    public static void main(String[] args) {
        System.out.print("enter health criteria for zoo");
        zoo = new Zoo(commandsHandler.readCriteria());

        commandsHandler.handle(zoo);
    }
}
