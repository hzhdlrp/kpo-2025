package org.example.domen.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.enums.PaymentTypes;

@Getter
@Builder
@AllArgsConstructor
public class Category {

    private int categoryId;
    private PaymentTypes categoryType;
    private String categoryName;
}
