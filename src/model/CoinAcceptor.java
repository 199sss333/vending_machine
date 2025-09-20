package model;

public class CoinAcceptor implements MoneyAcceptor {
    private int currentBalance;

    public CoinAcceptor(int currentBalance) {
        this.currentBalance = currentBalance;
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
