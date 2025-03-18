package org.example.fabrics;

import org.example.accounts.BankAccount;

import java.util.TreeSet;


public class AccountsFabric {
    public BankAccount createAccount(String name) {
        if (usedNames.contains(name)) {
            throw new RuntimeException("this name is already used\n");
        } else {
            while(usedIds.contains(id_counter)) id_counter++;
            usedIds.add(id_counter);
            return BankAccount.builder()
                    .balance(0)
                    .accountId(id_counter)
                    .accountName(name)
                    .build();
        }
    }

    private int id_counter;
    private TreeSet<String> usedNames;
    private TreeSet<Integer> usedIds;

    public AccountsFabric() {
        usedIds = new TreeSet<>();
        usedNames = new TreeSet<>();
        this.id_counter = 0;
    }
}
