package org.example.facades;

import org.example.domen.accounts.BankAccount;
import org.example.domen.categories.Category;
import org.example.enums.PaymentTypes;
import org.example.exporting.Exporter;
import org.example.fabrics.CategoryFabric;
import org.example.fabrics.OperationsFabric;
import org.example.domen.operations.Operation;
import org.example.importing.Importer;
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
    private HashMap<Integer, Operation> operations;
    private HashMap<String, Category> categoryByName;
    private CategoryFabric categoryFabric;

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
        this.operationsFabric = OperationsFabric.getInstance();
        bankAccountsNames = new HashMap<>();
        operations = new HashMap<>();
        categoryByName = new HashMap<>();
        categoryFabric = CategoryFabric.getInstance();
    }

    public void addCategory(String name, String type) {
        if (categoryByName.containsKey(name)) {
            System.out.println("category already exists\n");
            return;
        }
        Category category = categoryFabric.createCategory(name, type);
        if (category != null)
            categoryByName.put(name,categoryFabric.createCategory(name, type));
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
            Operation op = operationsFabric.createOperation(categoryName, bankAccountFacade.getAccountId(accountName), amount);
            operations.put(op.getOperationId(), op);
        }
        return -1;

    }

    public void changeCategoryName(String previous, String current) {
        if (!categoryByName.containsKey(previous)) {
            System.out.println("no such category\n");
            return;
        }
        categoryByName.put(current, categoryByName.get(previous));
        categoryByName.remove(previous);
    }

    public void exportAccounts(Exporter exporter) {
        exporter.writeAccounts(bankAccountFacade.getAccounts());
    }

    public void importAccounts(Importer importer, String file) {
        bankAccountFacade.getAccounts(importer.readAccounts(file));
    }

    public void exportCategories(Exporter exporter) {
        List<Category> categories = new ArrayList<>();
        categoryByName.values().forEach(category -> {
            categories.add(category);
        });
        exporter.writeCategories(categories);
    }

    public void importCategories(Importer importer, String file) {
        List<Category> categories = importer.readCategories(file);
        categories.forEach(category -> {
            if (!categoryByName.containsKey(category.getCategoryName())) {
                categoryByName.put(category.getCategoryName(), category);
            } else {
                System.out.println("category " + category.getCategoryName() + " already exists\n");
            }
        });
    }

    public void exportOperations(Exporter exporter) {
        List<Operation> operationsList = new ArrayList<>();
        operations.values().forEach(operation -> {
            operationsList.add(operation);
        });
        exporter.writeOperations(operationsList);
    }

    public void importOperations(Importer importer, String file) {
        List<Operation> operationsList = importer.readOperations(file);
        operationsList.forEach(operation -> {
            operations.put(operation.getOperationId(), operation);
            bankAccountFacade.changeAccountBalance(bankAccountsNames.get(operation.getBankAccountId()), operation.getAmount());
        });
    }
}
