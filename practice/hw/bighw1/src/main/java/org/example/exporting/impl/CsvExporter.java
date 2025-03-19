package org.example.exporting.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domen.accounts.BankAccount;
import org.example.domen.categories.Category;
import org.example.domen.operations.Operation;
import org.example.enums.PaymentTypes;
import org.example.exporting.Exporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter implements Exporter {

    @Override
    public void writeAccounts(List<BankAccount> accounts) {
        String filename = "accounts.csv";

        try (FileWriter writer = new FileWriter(filename)) {
            accounts.forEach(account -> {
                try {
                    writer.write(account.getAccountName());
                    writer.write(",");
                    writer.write(Integer.toString(account.getBalance()));
                    writer.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeOperations(List<Operation> operations) {
        String filename = "operations.csv";

        try (FileWriter writer = new FileWriter(filename)) {
            operations.forEach(operation -> {
                try {
                    writer.write(operation.getCategoryName());
                    writer.write(",");
                    writer.write(Integer.toString(operation.getBankAccountId()));
                    writer.write(",");
                    writer.write(Integer.toString(operation.getAmount()));
                    writer.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeCategories(List<Category> categories) {
        String filename = "categories.csv";

        try (FileWriter writer = new FileWriter(filename)) {
            categories.forEach(category -> {
                try {
                    writer.write(category.getCategoryName());
                    writer.write(",");
                    writer.write(category.getCategoryType() == PaymentTypes.EXPENSE ? "expense" : "income");
                    writer.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
