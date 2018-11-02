package programming3.pkg1;

import java.util.List;
public abstract class Game {

    private List<Player> players;

    private Player currentPlayer;

    private List<PlayerMove> moves;

    private GameRules CurrentRules;

    public abstract class GameRules 
    {

        public abstract int GetScoreChange(List<PlayerMove> moves);
        //public abstract Player DecideNextPlayer(List<PlayerMove> moves);
        public abstract String ChangePlayerStatus(PlayerMove move);
    }
    //public abstract void initGame();

    public abstract boolean AcceptMove(PlayerMove move);

    public abstract void ApplyPlayerMove(PlayerMove move);
}