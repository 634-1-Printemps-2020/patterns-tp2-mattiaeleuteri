package game;

import materials.Coin;
import materials.CoinState;
import player.Player;
import utils.Statistics;

import java.util.*;

public class Game {

    private Rules rules;
    private Coin coin;
    private Map<Player, List<CoinState>> history;

    public Game() {
        history = new HashMap<>();
        coin = Coin.getInstance();
        rules = Rules.getInstance();
    }

    /**
     * Ajouter un nouveau joueur au jeu
     *
     * @param player le nouveau joueur
     */
    public void addPlayer(Player player) {
      history.put(player, new ArrayList<>());
    }

    /**
     * Faire joueur tous les joueurs et stocker chaque partie dans history
     */
    public void play() {
      for(Player player : history.keySet()) {
          while (!rules.checkWin(history.get(player))){
              List<CoinState> partiesJouees = history.get(player);
              player.play(coin);
              partiesJouees.add(coin.getState());
              history.put(player, partiesJouees);
          }
      }
    }

    /**
     * Calculer des statistiques de la partie précédente
     *
     * @return Statistics
     */
    public Statistics getStatistics() {
        float averageToWin;
        int fewerMovesToWin = 100000;
        int mostMovesToWin = 0;
        int totalNumberMoves = 0;
        for (Player player : history.keySet()) {
            totalNumberMoves += history.get(player).size();
            if (history.get(player).size() < fewerMovesToWin) fewerMovesToWin = history.get(player).size();
            if (history.get(player).size() > mostMovesToWin) mostMovesToWin = history.get(player).size();
        }
        averageToWin = (float)totalNumberMoves/ history.keySet().size();

        return new Statistics(averageToWin, fewerMovesToWin, mostMovesToWin, totalNumberMoves);
    }

    /**
     * Obtenir l'historique de tous les joueurs de la partie précédente
     *
     * @return Map contenant chaque joueur et la liste des ses lancers
     */
    public Map<Player, List<CoinState>> getHistory() {
      return history;
    }


    /**
     * Obtenir l'historique d'un joueur spécifique
     *
     * @param player instance du joueur pour lequel on veut avoir l'historique
     * @return la liste des lancers d'un joueur
     */
    public List<CoinState> getSpecificHistory(Player player) {
      return history.get(player);
    }

}
