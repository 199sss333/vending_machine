package model;

public class CashAcceptor implements MoneyAcceptor {
    private int currentBalance;

    public CashAcceptor(int initialBalance) {
        this.currentBalance = initialBalance;
    }

    @Override
    public int getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public void giveMoney(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("Купюроприемник принимает только суммы кратные 10");
        }
        this.currentBalance += amount;
    }

    @Override
    public void removeMoney(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        if (amount > currentBalance) {
            throw new IllegalArgumentException("Недостаточно средств");
        }
        this.currentBalance -= amount;
    }
}

