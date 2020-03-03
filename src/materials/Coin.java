package materials;

import java.util.Random;

public class Coin {

  private CoinState coinState;
  private static Coin coin;
  private Random random = new Random();

  private Coin() {
    this.coinState = CoinState.values()[random.nextInt(CoinState.values().length)];
  }

  /**
   * Change l'état de la pièce.
   * 50% de probabilité d'obtenir HEADS, 50% de probabilité d'obtenir TAILS
   */
  public void throwCoin() {
    coinState = CoinState.values()[random.nextInt(CoinState.values().length)];
  }

  public static Coin getInstance(){
    if(coin == null) coin = new Coin();
     return coin;
  }

  public CoinState getState() {
    return coinState;
  }


}
