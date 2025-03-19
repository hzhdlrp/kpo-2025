package org.example.importing.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domen.accounts.BankAccount;
import org.example.domen.categories.Category;
import org.example.domen.operations.Operation;
import org.example.fabrics.AccountsFabric;
import org.example.fabrics.CategoryFabric;
import org.example.fabrics.OperationsFabric;
import org.example.importing.Importer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonImporter implements Importer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<BankAccount> readAccounts(String filename) {
        try {
            List<BankAccount> accounts = objectMapper.readValue(new File(filename), new TypeReference<List<BankAccount>>() {});
            return accounts;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Category> readCategories(String filename) {
        try {
            List<Category> categories = objectMapper.readValue(new File(filename), new TypeReference<List<Category>>() {});
            return categories;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public List<Operation> readOperations(String filename) {
        try {
            List<Operation> operations = objectMapper.readValue(new File(filename), new TypeReference<List<Operation>>() {});
            return operations;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

