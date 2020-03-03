package game;

import materials.CoinState;

import java.util.List;

public class Rules {

  private static Rules rules;

  private Rules(){}

  /**
   * Cette méthode permet de déterminer si une suite d'états de pièce permet de gagner à une partie
   * @param states liste d'états pour un joueur
   * @return true si un joueur a gagné, false sinon
   */
  public boolean checkWin(List<CoinState> states) {
    if(states.size() >= 3){
      return states.get(states.size() - 1) == CoinState.TAILS && states.get(states.size() - 2) == CoinState.TAILS && states.get(states.size() - 3) == CoinState.TAILS;
    }
    return false;
  }

  public static Rules getInstance(){
    if (rules == null) rules = new Rules();
    return rules;
  }
}
