package org.example.facades;

import org.example.categories.Category;
import org.example.enums.PaymentTypes;
import org.example.fabrics.OperationsFabric;
import org.example.operations.Operation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.TreeSet;

@Component
public class UserFacade {
    private BankAccountFacade bankAccountFacade;
    private HashMap<Integer, String> bankAccountsNames;
    private OperationsFabric operationsFabric;
    private HashMap<Integer, Operation> operations;
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
        bankAccountsNames = new HashMap<>();
        operations = new HashMap<>();
        categoryByName = new HashMap<>();
        usedCatIds = new TreeSet<>();
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
            System.out.println("unknown payment type, use \"income\" or \"expense\"\n");
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
        int id = bankAccountFacade.getAccountIdByName(previous);
        bankAccountFacade.changeAccountName(previous, current);
        if (id != -1) {
            bankAccountsNames.remove(id);
            bankAccountsNames.put(id, current);
        }
    }

    public void deleteOperationById(int id) {
        Operation op = operations.get(id);
        bankAccountFacade.changeAccountBalance(bankAccountsNames.get(op.getBankAccountId()), -op.getAmount());
        operations.remove(id);
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
            Operation op = operationsFabric.createOperation(categoryByName.get(categoryName), bankAccountFacade.getAccountId(accountName), amount);
            operations.put(op.getOperationId(), op);
        }
        return -1;

    }

    private int catIdCounter;
    private TreeSet<Integer> usedCatIds;

}
