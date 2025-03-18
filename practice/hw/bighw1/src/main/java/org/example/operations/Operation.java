package org.example.operations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.categories.Category;
import org.example.enums.PaymentTypes;
import java.time.ZonedDateTime;

import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Operation {

    private int operationId;
    private Category category;
    private int bankAccountId;
    private int amount;
    private ZonedDateTime date;
}
