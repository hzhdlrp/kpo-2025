package org.example.fabrics;

import org.example.enums.PaymentTypes;
import org.example.operations.Operation;

import java.time.ZonedDateTime;
import java.util.TreeSet;

public class OperationsFabric {
    public Operation createOperation(PaymentTypes type, int bankAccountId, int amount) {
        while(usedIds.contains(id_counter)) id_counter++;
        usedIds.add(id_counter);
        return new Operation(id_counter, type, bankAccountId, amount, ZonedDateTime.now());
    }

    private int id_counter;
    private TreeSet<Integer> usedIds;
    public OperationsFabric() {
        this.id_counter = 0;
    }
}
