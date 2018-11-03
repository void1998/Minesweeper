package programming3.pkg1;

import java.util.List;
public abstract class Game {

    protected List<Player> players;

    private Player currentPlayer;

    private List<PlayerMove> moves;

    private GameRules CurrentRules;
    
    private String GameStatus;

    public abstract class GameRules 
    {

        public abstract int GetScoreChange(PlayerMove move);
        //public abstract Player DecideNextPlayer(List<PlayerMove> moves);
        public abstract String ChangePlayerStatus(PlayerMove move);
    }
    public abstract void initGame(int playersNumber);

    public abstract boolean AcceptMove(PlayerMove move);

    public abstract void ApplyPlayerMove(PlayerMove move);

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setMoves(List<PlayerMove> moves) {
        this.moves = moves;
    }

    public void setCurrentRules(GameRules CurrentRules) {
        this.CurrentRules = CurrentRules;
    }

    public void setGameStatus(String GameStatus) {
        this.GameStatus = GameStatus;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<PlayerMove> getMoves() {
        return moves;
    }

    public GameRules getCurrentRules() {
        return CurrentRules;
    }

    public String getGameStatus() {
        return GameStatus;
    }
    
    
}