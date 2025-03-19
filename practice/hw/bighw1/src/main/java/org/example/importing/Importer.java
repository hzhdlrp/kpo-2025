package org.example.importing;

import org.example.domen.accounts.BankAccount;
import org.example.domen.categories.Category;
import org.example.domen.operations.Operation;

import java.util.List;

public interface Importer {
    public List<BankAccount> readAccounts(String fileName);
    public List<Category> readCategories(String filename);
    public List<Operation> readOperations(String filename);
}
