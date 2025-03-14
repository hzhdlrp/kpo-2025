package org.example.operations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.enums.PaymentTypes;
import java.time.ZonedDateTime;

import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Operation {

    private int operationId;
    private PaymentTypes type;
    private int bankAccountId;
    private int amount;
    private ZonedDateTime date;
}
