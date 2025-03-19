package org.example.facades;

import org.example.domen.accounts.BankAccount;
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


    public int getAccountIdByName(String name) {
        if (accountsMap.containsKey(name)) {
            return accountsMap.get(name).getAccountId();
        }
        return -1;
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

    public List<BankAccount> getAccounts() {
        List<BankAccount> accounts = new ArrayList<>();
        accountsMap.values().forEach(account -> {
            accounts.add(account);
        });
        return accounts;
    }

    public BankAccountFacade() {
        accountsMap = new HashMap<>();
        accountsFabric = AccountsFabric.getInstance();
    }

    public void getAccounts(List<BankAccount> accountList) {
        accountList.forEach(account -> {
            if (!accountsMap.containsKey(account.getAccountName())) {
                accountsMap.put(account.getAccountName(), account);
            } else {
                System.out.println("account with name " + account.getAccountName() + " already exists\n");
            }
        });
    }
}
