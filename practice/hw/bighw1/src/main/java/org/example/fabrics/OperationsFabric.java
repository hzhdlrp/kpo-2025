package org.example.fabrics;

import org.example.domen.categories.Category;
import org.example.domen.operations.Operation;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.TreeSet;

@Component
public class OperationsFabric {
    private static OperationsFabric instance;

    public static OperationsFabric getInstance() {
        if (instance == null) {
            instance = new OperationsFabric();
        }
        return instance;
    }

    public Operation createOperation(String category, int bankAccountId, int amount) {
        while(usedIds.contains(id_counter)) id_counter++;
        usedIds.add(id_counter);
        return new Operation(id_counter, category, bankAccountId, amount, ZonedDateTime.now());
    }

    private int id_counter;
    private TreeSet<Integer> usedIds;
    private OperationsFabric() {

        this.id_counter = 0;
        usedIds = new TreeSet<>();
    }
}
