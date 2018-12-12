package programming3.pkg1;

import programming3.pkg1.ExceptionPackage.IllegalGameMove;
import java.util.List;
import programming3.pkg1.GameTime.GameTime;
public abstract class Game {

    public List<Player> players;

    private Player currentPlayer;

    protected List<PlayerMove> moves;

    private GameRules CurrentRules;
    
    private String GameStatus;
    
    private int playersNumber;
    
    protected GameTime gameTime;

    public abstract class GameRules 
    {

        public abstract int GetScoreChange(PlayerMove move);
        public abstract Player DecideNextPlayer(Player currentPlayer);
        public abstract String ChangePlayerStatus(PlayerMove move);
    }
    public abstract void initGame(int playersNumber, int isAuto, int numberOfShieldsForPlayer);

    public abstract String AcceptMove(PlayerMove move, Grid myGrid) throws IllegalGameMove;

    public abstract void ApplyPlayerMove(List<PlayerMove> moves, Grid myGrid);
    
    public abstract void Winner();
    
    public abstract void DFS(int x,int y, Grid myGrid);
    
    public abstract String checkGame(Grid myGrid);

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

    public void setGameTime(GameTime gameTime) {
        this.gameTime = gameTime;
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

    public int getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public GameTime getGameTime() {
        return gameTime;
    }
    
}