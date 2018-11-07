package programming3.pkg1;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grid {

    private Square[][] squares/*=new Square[21][21]*/;

    private Game currentGame;
    
    GameException gameException;
    
    private int hieght;
            
    private int width;

    public Grid() {
    }

    public Grid(Square[][] squares, Game currentGame) {
        this.squares = squares;
        this.currentGame = currentGame;
    }
    
    public Grid(int hieght, int width, Game currentGame)
    {
        squares = new Square[hieght+2][width+2];
        for(int i=0;i<hieght+2;i++)
        {
            for(int j=0;j<width+2;j++)
            {
                squares[i][j] = new Square();
                squares[i][j].setX(i);
                squares[i][j].setY(j);
            }
        }
        this.currentGame = currentGame;
        this.hieght = hieght;
        this.width = width;
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

    
    
    public void initGrid(Square initSquare) {
    setBorder();
    generateMines(initSquare);
    fillUpNumbers();
    
    }

    public void AcceptMove(PlayerMove move) {
        
        try 
        {
            String accept = currentGame.AcceptMove(move,this);
            if(!accept.equals(Constants.TRUE))
            {
                throw new IllegalGameMove(accept);
            }
            int scoreChange;
            int score=move.getPlayer().getCurrentScore().getRealScore();
            String status;
            move.square.squareStatus = this.getSquares()[move.getSquare().getX()][move.getSquare().getY()].squareStatus;
            currentGame.moves.add(move);
            currentGame.ApplyPlayerMove(currentGame.moves,this);
            scoreChange = currentGame.getCurrentRules().GetScoreChange(move);
            move.getResult().setScoreChange(scoreChange);
            move.getPlayer().getCurrentScore().setRealScore(score + scoreChange);
            status = currentGame.getCurrentRules().ChangePlayerStatus(move);
            move.getPlayer().getCurrentStatue().setStatus(status);
            currentGame.setCurrentPlayer(currentGame.getCurrentRules().DecideNextPlayer(currentGame.moves));
       
        } 
        catch (IllegalGameMove ex) 
        {
            System.out.println(ex.getException());
        }
            
    }
    
    /*helper methods*/
    public void setBorder()
    {
      //  set the border around the grid as a mines
            for(int i=0;i<hieght+2;i++)
        {
            for(int j=0;j<width+2;j++)
            {
                if(i==0||j==0||i==hieght+1||j==width+1)
                {
                    squares[i][j].getSquareStatus().setValue(9);
                }
            }
        }
    }
    public void generateMines(Square initSquare)
    {
        // coordinats of squares that located around current square
      Integer[][] coordinator=new Integer[][]{{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
      int count=0;
       Random random=new Random();
       //generat 19 mines
       for(int mineNumber=0;mineNumber<35;mineNumber++)
      {
       while(true)
       {
       int x=random.nextInt(hieght)+1;
       int y=random.nextInt(width)+1;
       if(!squares[x][y].IsMine())
       {  
           //if not mine
           if(!squares[x][y].equals(initSquare))
           {
               // if not the initiated square
                for(int i=0;i<8;i++)
                   {
                       if(squares[x+coordinator[i][0]][y+coordinator[i][1]].equals(initSquare))
                       {
                           
                           count=10;
                       }
                       if(squares[x+coordinator[i][0]][y+coordinator[i][1]].IsMine())
                       count++;
                   }
                if(count<8)
                   {
                       squares[x][y].getSquareStatus().setValue(9);
                       count=0;
                       break;
                   }
           }
        }
        count=0;
       }
       }
    }
    
    
    public void fillUpNumbers()
    {
        Integer[][] coordinator=new Integer[][]{{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        for(int i=1;i<hieght+1;i++)
        {
            for(int j=1;j<width+1;j++)
            {
                if(squares[i][j].IsMine())
                {
                   for(int coordinat=0;coordinat<8;coordinat++)
                   {
                       int tempX=i+coordinator[coordinat][0];
                       int tempY=j+coordinator[coordinat][1];
                       if(squares[tempX][tempY].getSquareStatus().getValue()!=9)
                            squares[tempX][tempY].getSquareStatus().setValue(squares[tempX][tempY].getSquareStatus().getValue()+1);
                   }
                }
            }
        }
    }
    
    public boolean checkGame()
    {
        String state;
        for(int i=1;i<squares.length-1;i++)
        {
            for(int j=1;j<squares.length-1;j++)
            {
                state = squares[i][j].getSquareStatus().getStatus();
                if(state.equals(Constants.CLOSED) || state.equals(Constants.OPENED_MINE))
                {
                    return false;
                }
            }
        }
        currentGame.setGameStatus(Constants.FINISHED);
        return true;
    }
}
