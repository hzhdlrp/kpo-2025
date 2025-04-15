package application.app;

import domain.animals.herbos.Monkey;
import domain.animals.herbos.Rabbit;
import domain.animals.predators.Tiger;
import domain.animals.predators.Wolf;
import org.springframework.stereotype.Component;
import domain.things.Computer;
import domain.things.Table;

import java.util.Scanner;

@Component
public class CommandsHandler {
    public void handle(Zoo zoo) {
        while (handleOneCommand(zoo));
    }

    public int readCriteria() {
        return scanner.nextInt();
    }

    public boolean handleOneCommand(Zoo zoo) {
        System.out.println("type command:");
        String word = scanner.next();
        if (word.equals("end")) {
            return false;
        }


        if (word.equals("add")) {
            word = scanner.next();
            if (word.equals("animal")) {
                word = scanner.next();
                int health = scanner.nextInt();
                String nickname = scanner.next();
                String sex = scanner.next();
                String favouriteFood = scanner.next();
                try {
                    createAnimal(zoo, word, health, nickname, sex, favouriteFood);
                } catch (RuntimeException e) {
                    System.out.println(e.getCause());
                }
            } else if (word.equals("thing")) {
                word = scanner.next();

                createThing(zoo, word);
            } else {
                System.out.println("unknown \"add\" argument");
                scanner.nextLine();
            }
        } else if (word.equals("print")) {
            word = scanner.next();
            if (word.equals("animals")) {
                zoo.printAnimalsInfo();
            } else if (word.equals("things")) {
                zoo.printThingsInfo();
            } else if (word.equals("contact")) {
                zoo.printContactList();
            } else if (word.equals("food")) {
                zoo.printAllFoodCount();
            } else {
                System.out.println("unknown \"print\" argument");
                scanner.nextLine();
            }
        } else {
            System.out.println("unknown command");
            scanner.nextLine();
            System.out.println(word);
        }

        return true;
    }

    private void createAnimal(Zoo zoo, String type, int health, String nickname,
                              String sex, String favoriteFood) {
        if (type.equals("monkey")) {
            zoo.addAnimal(new Monkey(health, nickname, sex, favoriteFood));
            return;
        }
        if (type.equals("rabbit")) {
            zoo.addAnimal(new Rabbit(health, nickname, sex, favoriteFood));
            return;
        }
        if (type.equals("tiger")) {
            zoo.addAnimal(new Tiger(health, nickname, sex, favoriteFood));
            return;
        }
        if (type.equals("wolf")) {
            zoo.addAnimal(new Wolf(health, nickname, sex, favoriteFood));
            return;
        }
        System.out.println("unknown animal");
        scanner.nextLine();
    }

    private void createThing(Zoo zoo, String type) {
        if (type.equals("table")) {
            zoo.addThing(new Table());
            return;
        }
        if (type.equals("computer")) {
            zoo.addThing(new Computer());
            return;
        }
        System.out.println("unknown thing");
        scanner.nextLine();
    }

    private Scanner scanner = new Scanner(System.in);
}
