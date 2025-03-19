package org.example.fabrics;

import org.example.domen.categories.Category;
import org.example.enums.PaymentTypes;

import java.util.TreeSet;

public class CategoryFabric {
    private static CategoryFabric instance;

    public static CategoryFabric getInstance() {
        if (instance == null) {
            instance = new CategoryFabric();
        }
        return instance;
    }

    public Category createCategory(String name, String type) {
        if (usedNames.contains(name)) {
            throw new RuntimeException("this name is already used\n");
        } else {
            while(usedIds.contains(id_counter)) id_counter++;
            usedIds.add(id_counter);
            PaymentTypes pType;
            if (type.equals("income")) {
                pType = PaymentTypes.INCOME;
            } else if(type.equals("expense")) {
                pType = PaymentTypes.EXPENSE;
            } else {
                System.out.println("unknown payment type, use \"income\" or \"expense\"\n");
                return null;
            }

            return Category.builder()
                    .categoryId(id_counter)
                    .categoryName(name)
                    .categoryType(pType)
                    .build();
        }
    }

    private int id_counter;
    private TreeSet<String> usedNames;
    private TreeSet<Integer> usedIds;

    private CategoryFabric() {
        usedIds = new TreeSet<>();
        usedNames = new TreeSet<>();
        this.id_counter = 0;
    }
}
