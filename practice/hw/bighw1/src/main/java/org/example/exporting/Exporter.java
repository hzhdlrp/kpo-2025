package org.example.exporting;

import org.example.domen.accounts.BankAccount;
import org.example.domen.categories.Category;
import org.example.domen.operations.Operation;

import java.util.List;

public interface Exporter {
    public void writeAccounts(List<BankAccount> accounts);
    public void writeCategories(List<Category> categories);
    public void writeOperations(List<Operation> operations);
}
