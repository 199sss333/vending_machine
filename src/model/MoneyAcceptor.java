package model;

public interface MoneyAcceptor {
    int getCurrentBalance();
    void giveMoney(int amount);
    void removeMoney(int amount);

}
