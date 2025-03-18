package org.example.service;

import org.example.facades.UserFacade;

import java.util.Scanner;

public class CommandsHandler {
    public void handle(UserFacade facade) {
        while (handleOneCommand(facade));
    }

    private boolean handleOneCommand(UserFacade facade) {
        System.out.println("type command:");
        String word = scanner.next();
        if (word.equals("end")) {
            return false;
        }


        if (word.equals("create")) {
            word = scanner.next();
            if (word.equals("account")) {
                word = scanner.next();
                facade.createAccount(word);
            } else if (word.equals("category")) {
                word = scanner.next();
                String type = scanner.next();
                facade.addCategory(word, type);
            } else if (word.equals("operation")) {
                String accountName = scanner.next();
                String categoryName = scanner.next();
                int amount = scanner.nextInt();
                int id = facade.addOperation(accountName, categoryName, amount);
                if (id != -1) {
                    System.out.println(id);
                }
            } else {
                System.out.println("unknown \"create\" argument");
                scanner.nextLine();
            }
        } else if (word.equals("delete")) {
            word = scanner.next();
            if (word.equals("category")) {
                word = scanner.next();
                facade.deleteCategory(word);
            } else if (word.equals("account")) {
                word = scanner.next();
                facade.deleteAccount(word);
            } else if (word.equals("operation")) {
                int id = scanner.nextInt();
                facade.deleteOperationById(id);
            } else {
                System.out.println("unknown \"delete\" argument");
                scanner.nextLine();
            }
        } else if (word.equals("change")) {
            word = scanner.next();
            if (word.equals("account")) {
                word = scanner.next();
                if (word.equals("name")) {
                    String name = scanner.next();
                    String new_name = scanner.next();
                    facade.changeAccountName(name, new_name);
                } else {
                    System.out.println("unknown \"delete\" argument");
                    scanner.nextLine();
                }
            } else {
                System.out.println("unknown \"delete\" argument");
                scanner.nextLine();
            }
        } else {
            System.out.println("unknown command");
            scanner.nextLine();
            System.out.println(word);
        }

        return true;
    }

    private Scanner scanner = new Scanner(System.in);
}
