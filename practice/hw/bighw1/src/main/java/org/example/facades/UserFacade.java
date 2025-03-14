package org.example.facades;

import org.example.accounts.BankAccount;
import org.example.categories.Category;
import org.example.enums.PaymentTypes;
import org.example.fabrics.OperationsFabric;
import org.example.facades.BankAccountFacade;
import org.example.operations.Operation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

@Component
public class UserFacade {
    private BankAccountFacade bankAccountFacade;
    private HashMap<Integer, String> bankAccountsNames;
    private OperationsFabric operationsFabric;
    private HashMap<String, HashMap<String, List<Operation>>> operationsByCategories;
    private HashMap<String, Category> categoryByName;

    public void createAccount(String name) {
        int id = bankAccountFacade.addNewAccount(name);
        if (id != -1) {
            System.out.println("success\n");
            bankAccountsNames.put(id, name);
        } else {
            System.out.println("error, try again please\n");
        }
    }

    public UserFacade() {
        bankAccountFacade = new BankAccountFacade();
        this.operationsFabric = new OperationsFabric();
        catIdCounter = 0;
    }

    public void addCategory(String name, String type) {
        if (categoryByName.containsKey(name)) {
            System.out.println("category already exists\n");
            return;
        }
        PaymentTypes pType;
        if (type.equals("income")) {
            pType = PaymentTypes.INCOME;
        } else if(type.equals("expense")) {
            pType = PaymentTypes.EXPENSE;
        } else {
            System.out.println("unknown payment type, use \"income\" or \"outcome\"\n");
            return;
        }
        while(usedCatIds.contains(catIdCounter)) catIdCounter++;
        usedCatIds.add(catIdCounter);

        categoryByName.put(name, Category.builder()
                .categoryId(catIdCounter)
                .categoryType(pType)
                .categoryName(name)
                .build());
    }

    public void deleteCategory(String name) {
        if (categoryByName.containsKey(name)) categoryByName.remove(name);
    }

    public void deleteAccount(String name) {
        bankAccountFacade.deleteAccount(name);
    }

    public void changeAccountName(String previous, String current) {
        bankAccountFacade.changeAccountName(previous, current);
    }

    public void deleteOperationById(int id) {
        operationsByCategories.values().forEach(map -> {
            map.values().forEach(list -> {
                list.forEach(operation -> {
                    if (operation.getOperationId() == id) list.remove(operation);
                });
            });
        });
    }

    public int addOperation(String accountName, String categoryName, int amount) {
        if (!categoryByName.containsKey(categoryName)) {
            System.out.println("no such category\n");
            return -1;
        }
        if (categoryByName.get(categoryName).getCategoryType() == PaymentTypes.EXPENSE) {
            if (amount > 0) {
                System.out.println("expense can not be positive\n");
                return -1;
            }
        } else {
            if (amount < 0) {
                System.out.println("income can not be negative\n");
                return -1;
            }
        }
        if (bankAccountFacade.changeAccountBalance(accountName, amount) != -1) {
            if (!operationsByCategories.containsKey(accountName)) {
                operationsByCategories.put(accountName, new HashMap<>());
            }
            if (!operationsByCategories.get(accountName).containsKey(categoryName)) {
                operationsByCategories.get(accountName).put(categoryName, new ArrayList<>());
            }
            Operation operation = operationsFabric.createOperation(
                    categoryByName.get(categoryName).getCategoryType(),
                    bankAccountFacade.getAccountId(accountName),
                    amount);

            operationsByCategories.get(accountName).get(categoryName).add(operation);
            return operation.getOperationId();
        }

    }

    private int catIdCounter;
    private TreeSet<Integer> usedCatIds;

}
