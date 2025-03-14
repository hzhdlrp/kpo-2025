package org.example.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Builder
@Getter
@AllArgsConstructor
public class BankAccount {

    private int accountId;
    private String accountName;
    private int balance;

    /**
     * @param change
     * @return 0 if success, -1 else
     */
    public int changeBalance(int change) {
        if (balance + change >= 0) {
            balance += change;
            return 0;
        }
        System.out.println("error, not enough money\n");
        return -1;
    }

    public void changeAccountName(String newName) {
        this.accountName = newName;
    }
}
