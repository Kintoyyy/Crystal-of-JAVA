package Entities.Common;

public class Coin {
    private int coins;

    public Coin() {
        this.coins = 0;
    }

    public Coin(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public void removeCoins(int coins) {
        this.coins -= coins;
    }

    public boolean hasEnoughCoins(int coins) {
        return this.coins >= coins;
    }

    public void resetCoins() {
        this.coins = 0;
    }

    public void resetCoins(int coins) {
        this.coins = coins;
    }

    public void resetCoins(Coin coin) {
        this.coins = coin.getCoins();
    }

    public void resetCoins(Coin coin, int coins) {
        this.coins = coin.getCoins() + coins;
    }

    public void resetCoins(int coins, Coin coin) {
        this.coins = coins + coin.getCoins();
    }
}
