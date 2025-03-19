package org.example.exporting.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domen.accounts.BankAccount;
import org.example.domen.categories.Category;
import org.example.domen.operations.Operation;
import org.example.exporting.Exporter;
import org.example.fabrics.CategoryFabric;
import org.example.fabrics.OperationsFabric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonExporter implements Exporter {
    @Override
    public void writeAccounts(List<BankAccount> accounts) {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("accounts.json");

        try {
            objectMapper.writeValue(file, accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeOperations(List<Operation> operations) {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("operations.json");

        try {
            objectMapper.writeValue(file, operations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeCategories(List<Category> categories) {
        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("categories.json");

        try {
            objectMapper.writeValue(file, categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
