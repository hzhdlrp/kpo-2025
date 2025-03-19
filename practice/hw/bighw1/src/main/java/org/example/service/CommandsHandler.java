package org.example.service;

import org.example.exporting.impl.CsvExporter;
import org.example.exporting.impl.JsonExporter;
import org.example.facades.UserFacade;
import org.example.importing.impl.CsvImporter;
import org.example.importing.impl.JsonImporter;

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
                    System.out.println("unknown \"change\" arguments");
                    scanner.nextLine();
                }
            } if (word.equals("category")) {
                word = scanner.next();
                if (word.equals("name")) {
                    String name = scanner.next();
                    String new_name = scanner.next();
                    facade.changeCategoryName(name, new_name);
                } else {
                    System.out.println("unknown \"change\" arguments");
                    scanner.nextLine();
                }
            } else {
                System.out.println("unknown \"change\" arguments");
                scanner.nextLine();
            }
        } else if (word.equals("import")) {
            String type = scanner.next();
            if (type.equals("json")) {
                word = scanner.next();
                if (word.equals("operations")) {
                    word = scanner.next();
                    facade.importOperations(new JsonImporter(), word);
                } else if (word.equals("accounts")) {
                    word = scanner.next();
                    facade.importAccounts(new JsonImporter(), word);
                } else if (word.equals("categories")) {
                    word = scanner.next();
                    facade.importCategories(new JsonImporter(), word);
                } else {
                    System.out.println("unknown args of import\n");
                }
            } else if (word.equals("csv")) {
                word = scanner.next();
                if (word.equals("operations")) {
                    word = scanner.next();
                    facade.importOperations(new CsvImporter(), word);
                } else if (word.equals("accounts")) {
                    word = scanner.next();
                    facade.importAccounts(new CsvImporter(), word);
                } else if (word.equals("categories")) {
                    word = scanner.next();
                    facade.importCategories(new CsvImporter(), word);
                } else {
                    System.out.println("unknown args of import\n");
                }
            } else {
                System.out.println("unknown args of import\n");
            }
        } else if (word.equals("export")) {
            String type = scanner.next();
            if (type.equals("json")) {
                word = scanner.next();
                if (word.equals("operations")) {
                    facade.exportOperations(new JsonExporter());
                } else if (word.equals("accounts")) {
                    facade.exportAccounts(new JsonExporter());
                } else if (word.equals("categories")) {
                    facade.exportCategories(new JsonExporter());
                } else {
                    System.out.println("unknown args of import\n");
                }
            } else if (word.equals("csv")) {
                word = scanner.next();
                if (word.equals("operations")) {
                    facade.exportOperations(new CsvExporter());
                } else if (word.equals("accounts")) {
                    facade.exportAccounts(new CsvExporter());
                } else if (word.equals("categories")) {
                    facade.exportCategories(new CsvExporter());
                } else {
                    System.out.println("unknown args of import\n");
                }
            } else {
                System.out.println("unknown args of import\n");
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
