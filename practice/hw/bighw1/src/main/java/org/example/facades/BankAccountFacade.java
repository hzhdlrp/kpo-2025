package org.example.facades;

import org.example.accounts.BankAccount;
import org.example.fabrics.AccountsFabric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankAccountFacade {
    private AccountsFabric accountsFabric;
    private HashMap<String, BankAccount> accountsMap;

    /**
     * @param name
     * @return accountId if success, -1 else
     */
    public int addNewAccount(String name) {
        BankAccount account;
        try {
            account = accountsFabric.createAccount(name);
            accountsMap.put(name, account);
            return account.getAccountId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void changeAccountName(String previous, String current) {
        if (accountsMap.containsKey(previous)) {
            BankAccount account = accountsMap.get(previous);
            account.changeAccountName(current);
            accountsMap.remove(previous);
            accountsMap.put(current, account);
        } else {
            System.out.println("no such account\n");
        }
    }

    public int changeAccountBalance(String name, int change) {
        if (accountsMap.containsKey(name)) {
            return accountsMap.get(name).changeBalance(change);
        } else {
            System.out.println("no account with this name\n");
            return -1;
        }
    }

    public boolean ifAccountExists(String name) {
        return accountsMap.containsKey(name);
    }

    public void deleteAccount(String name) {
        if (accountsMap.containsKey(name)) accountsMap.remove(name);
    }

    public int getAccountId(String name) {
        if (accountsMap.containsKey(name)) {
            return accountsMap.get(name).getAccountId();
        } else {
            System.out.println("no such account\n");
            return -1;
        }
    }

    public BankAccountFacade() {
        accountsMap = new HashMap<>();
        accountsFabric = new AccountsFabric();
    }
}
