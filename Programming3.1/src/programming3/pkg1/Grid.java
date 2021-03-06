package programming3.pkg1;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import programming3.pkg1.UtilPackage.Constants;
import programming3.pkg1.ExceptionPackage.IllegalGameMove;
import programming3.pkg1.ExceptionPackage.GameException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import programming3.pkg1.Floders.Folders;
import programming3.pkg1.Game;
import programming3.pkg1.Game;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.ShieldPackage.Shield;
import programming3.pkg1.ShieldPackage.Shield100;
import programming3.pkg1.ShieldPackage.Shield200;
import programming3.pkg1.ShieldPackage.Shield50;
import programming3.pkg1.Timerhelperspackage.MoveTimer;

public class Grid implements Serializable {

    MoveTimer Time = new MoveTimer();
    private Square[][] squares/*=new Square[21][21]*/;

    private Game currentGame;

    GameException gameException;

    private int hieght;

    private int width;

    protected List<Shield> shieldsPlaces;

    protected int numberOfShieldsInGired;
    
    protected int gameNumber;
    
    //protected Folders folder;

    public Grid() {
    }

    public Grid(Square[][] squares, Game currentGame) {
        this.squares = squares;
        this.currentGame = currentGame;
    }

    public Grid(int hieght, int width, Game currentGame, int numberOfShields) {
        squares = new Square[hieght + 2][width + 2];
        for (int i = 0; i < hieght + 2; i++) {
            for (int j = 0; j < width + 2; j++) {
                squares[i][j] = new Square();
                squares[i][j].setX(i);
                squares[i][j].setY(j);
            }
        }
        this.currentGame = currentGame;
        this.hieght = hieght;
        this.width = width;
        this.numberOfShieldsInGired = numberOfShields;
        try {
            this.gameNumber = Folders.readNumber();
        } catch (IOException ex) {
            this.gameNumber = 0;
        } catch (ClassNotFoundException ex) {
            this.gameNumber = 0;
        }
        try {
            Folders.writeNumber(gameNumber+1);
        } catch (IOException ex) {
            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public int getHieght() {
        return hieght;
    }

    public int getWidth() {
        return width;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(int gameNumber) {
        this.gameNumber = gameNumber;
    }
    
    public List<Shield> getShieldsPlaces() {
        return shieldsPlaces;
    }

    public void initGrid(Square initSquare) {
        setBorder();
        generateMines(initSquare);
        fillUpNumbers();
        GenerateShields(this.numberOfShieldsInGired);

    }

    public void AcceptMove(PlayerMove move) {

        try {
            String accept = currentGame.AcceptMove(move, this);
            if (!accept.equals(Constants.TRUE)) {
                throw new IllegalGameMove(accept);
            }
            int scoreChange;
            int score = move.getPlayer().getCurrentScore().getRealScore();
            String status;
            move.square.squareStatus = this.getSquares()[move.getSquare().getX()][move.getSquare().getY()].squareStatus;
            //currentGame.moves.add(move);
            currentGame.ApplyPlayerMove(move, this);
            scoreChange = currentGame.getCurrentRules().GetScoreChange(move);
            //changing the score for the player
            move.getResult().setScoreChange(scoreChange);
            move.getPlayer().getCurrentScore().setRealScore(score + scoreChange);
            move.getResult().setTotalScore(move.getPlayer().getCurrentScore().getRealScore());
            //changing the status of the player
            status = currentGame.getCurrentRules().ChangePlayerStatus(move);
            move.getPlayer().getCurrentStatue().setStatus(status);
            // for taking the shields from the opened squares
            if (!shieldsPlaces.isEmpty() && !getCurrentGame().getCurrentPlayer().getName().equals("Auto Player")) {
                int x = 0, y = 0;
                Shield shield;
                String state;
                for (int i = 0; i < shieldsPlaces.size(); i++) {
                    shield = shieldsPlaces.get(i);
                    x = shield.getX();
                    y = shield.getY();
                    state = squares[x][y].getSquareStatus().getStatus();
                    if (!state.equals(Constants.CLOSED) && !state.equals(Constants.MARKED)) {
                        shield.setX(0);
                        shield.setY(0);
                        currentGame.getCurrentPlayer().shields.add(shield);
                        shieldsPlaces.remove(i);
                        i--;
                    }
                }
            }
            currentGame.setCurrentPlayer(currentGame.getCurrentRules().DecideNextPlayer(currentGame.getCurrentPlayer()));
            currentGame.moves.add(move);
            try 
            {
                Folders.quickSave(this);
                System.out.println("quick saved");
            } catch (IOException ex) {
                Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IllegalGameMove ex) {
            System.out.println(ex.getException());
        }

    }

    public void ss() {
        currentGame.setCurrentPlayer(currentGame.getCurrentRules().DecideNextPlayer(currentGame.getCurrentPlayer()));

    }

    /*helper methods*/
    public void setBorder() {
        //  set the border around the grid as a mines
        for (int i = 0; i < hieght + 2; i++) {
            for (int j = 0; j < width + 2; j++) {
                if (i == 0 || j == 0 || i == hieght + 1 || j == width + 1) {
                    squares[i][j].getSquareStatus().setValue(9);
                }
            }
        }
    }

    public void generateMines(Square initSquare) {
        // coordinats of squares that located around current square
        Integer[][] coordinator = new Integer[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        int count = 0;
        Random random = new Random();
        //generat 19 mines
        for (int mineNumber = 0; mineNumber < 35; mineNumber++) {
            while (true) {
                int x = random.nextInt(hieght) + 1;
                int y = random.nextInt(width) + 1;
                if (!squares[x][y].IsMine()) {
                    //if not mine
                    if (!squares[x][y].equals(initSquare)) {
                        // if not the initiated square
                        for (int i = 0; i < 8; i++) {
                            if (squares[x + coordinator[i][0]][y + coordinator[i][1]].equals(initSquare)) {

                                count = 10;
                            }
                            if (squares[x + coordinator[i][0]][y + coordinator[i][1]].IsMine()) {
                                count++;
                            }
                        }
                        if (count < 8) {
                            squares[x][y].getSquareStatus().setValue(9);
                            count = 0;
                            break;
                        }
                    }
                }
                count = 0;
            }
        }
    }

    public void fillUpNumbers() {
        Integer[][] coordinator = new Integer[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int i = 1; i < hieght + 1; i++) {
            for (int j = 1; j < width + 1; j++) {
                if (squares[i][j].IsMine()) {
                    for (int coordinat = 0; coordinat < 8; coordinat++) {
                        int tempX = i + coordinator[coordinat][0];
                        int tempY = j + coordinator[coordinat][1];
                        if (squares[tempX][tempY].getSquareStatus().getValue() != 9) {
                            squares[tempX][tempY].getSquareStatus().setValue(squares[tempX][tempY].getSquareStatus().getValue() + 1);
                        }
                    }
                }
            }
        }
    }

    public void GenerateShields(int shieldsNumber) {
        int shieldType1 = 0, shieldType2 = 0, shieldType3 = 0, count = 3, number = shieldsNumber;
        while (count > 0) {
            if (count == 3) {
                shieldType1 = number / count;
                number -= shieldType1;
            } else if (count == 2) {
                shieldType2 = number / count;
                number -= shieldType2;
            } else {
                shieldType3 = number / count;
                number -= shieldType3;
            }
            count--;
        }
        shieldsPlaces = new ArrayList<>();
        Random random = new Random();
        for (int shield1 = 0; shield1 < shieldType1; shield1++) {
            int x = random.nextInt(hieght) + 1;
            int y = random.nextInt(width) + 1;
            if (squares[x][y].getSquareStatus().getValue() != 9 && squares[x][y].getSquareStatus().shield == null) {
                Shield shield = new Shield50();
                shield.setX(x);
                shield.setY(y);
                squares[x][y].getSquareStatus().shield = shield;
                shieldsPlaces.add(shield);
            }
        }
        for (int shield2 = 0; shield2 < shieldType2; shield2++) {
            int x = random.nextInt(hieght) + 1;
            int y = random.nextInt(width) + 1;
            if (squares[x][y].getSquareStatus().getValue() != 9 && squares[x][y].getSquareStatus().shield == null) {
                Shield shield = new Shield100();
                shield.setX(x);
                shield.setY(y);
                squares[x][y].getSquareStatus().shield = shield;
                shieldsPlaces.add(shield);
            }
        }
        for (int shield3 = 0; shield3 < shieldType3; shield3++) {
            int x = random.nextInt(hieght) + 1;
            int y = random.nextInt(width) + 1;
            if (squares[x][y].getSquareStatus().getValue() != 9 && squares[x][y].getSquareStatus().shield == null) {
                Shield shield = new Shield200();
                shield.setX(x);
                shield.setY(y);
                squares[x][y].getSquareStatus().shield = shield;
                shieldsPlaces.add(shield);
            }
        }
    }
}
