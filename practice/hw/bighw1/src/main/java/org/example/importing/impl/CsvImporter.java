package org.example.importing.impl;

import org.example.domen.accounts.BankAccount;
import org.example.domen.categories.Category;
import org.example.fabrics.AccountsFabric;
import org.example.fabrics.CategoryFabric;
import org.example.fabrics.OperationsFabric;
import org.example.importing.Importer;
import org.example.domen.operations.Operation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvImporter implements Importer {
    @Override
    public List<BankAccount> readAccounts(String filename) {
        List<BankAccount> accounts = new ArrayList<>();

        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                BankAccount account = AccountsFabric.getInstance().createAccount(data[0]);
                account.changeBalance(Integer.parseInt(data[1]));
                accounts.add(account);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public List<Category> readCategories(String filename) {
        List<Category> categories = new ArrayList<>();

        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                Category category = CategoryFabric.getInstance().createCategory(data[0], data[1]);
                if (category != null) {
                    categories.add(category);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public List<Operation> readOperations(String filename) {
        List<Operation> operations = new ArrayList<>();

        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                Operation operation = OperationsFabric.getInstance().createOperation(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                operations.add(operation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return operations;
    }
}
