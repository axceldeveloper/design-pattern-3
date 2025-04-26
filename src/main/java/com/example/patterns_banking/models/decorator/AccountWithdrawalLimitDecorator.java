package com.example.patterns_banking.models.decorator;

import com.example.patterns_banking.models.Account;

public class AccountWithdrawalLimitDecorator extends AccountDecorator {
    private static final double WITHDRAWAL_LIMIT = 20000.0;

    public AccountWithdrawalLimitDecorator(Account account) {
        super(account);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > WITHDRAWAL_LIMIT) {
            double excess = amount - WITHDRAWAL_LIMIT;
            System.out.println("Se hizo el retiro y el excedente fue de: " + excess);
            super.setBalance(0.0);
        } else {
            super.withdraw(amount);
        }
    }

}
