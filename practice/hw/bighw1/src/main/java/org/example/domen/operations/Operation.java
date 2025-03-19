package org.example.domen.operations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.domen.categories.Category;

import java.time.ZonedDateTime;

import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Operation {

    private int operationId;
    private String categoryName;
    private int bankAccountId;
    private int amount;
    private ZonedDateTime date;
}
