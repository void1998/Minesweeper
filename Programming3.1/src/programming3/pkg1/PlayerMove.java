package programming3.pkg1;

public class PlayerMove {

    private Player player;

    Square square;

    private MoveType move;

    private MoveResult result;

    public PlayerMove() {
       move = new MoveType();
       result = new MoveResult();
       square = new Square();
        
    }

    public PlayerMove(Player player, Square square, MoveType move, MoveResult result) {
        this.player = player;
        this.square = square;
        this.move = move;
        this.result = result;
    }

    
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public void setMove(MoveType move) {
        this.move = move;
    }

    public void setResult(MoveResult result) {
        this.result = result;
    }

    
    public Square getSquare() {
        return square;
    }

    public Player getPlayer() {
        return player;
    }

    public MoveType getMove() {
        return move;
    }

    public MoveResult getResult() {
        return result;
    }
    
    
}
