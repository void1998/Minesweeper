package programming3.pkg1;

import java.util.Random;

public class Grid {

    private Square[][] squares=new Square[21][21];

    private Game currentGame;
    
    GameException gameException;
    

    public Grid() {
    }

    public Grid(Square[][] squares, Game currentGame) {
        this.squares = squares;
        this.currentGame = currentGame;
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

    
    
    public void initGrid(Square initSquare) {
    setBorder();
    generateMines(initSquare);
    fillUpNumbers();
    
    }

    public void AcceptMove(PlayerMove move) {
        if(currentGame.AcceptMove(move))
            currentGame.ApplyPlayerMove(move);
        else 
            gameException.handleEx();
            
    }
    
    
    /*helper methods*/
    public void setBorder()
    {
      //  set the border around the grid as a mines
            for(int i=0;i<squares.length;i++)
        {
            for(int j=0;j<squares.length;i++)
            {
                if(i==0||j==0||i==squares.length-1||j==squares.length-1)
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
       for(int mineNumber=0;mineNumber<19;mineNumber++)
      {
       while(true)
       {
       int x=random.nextInt()*(squares.length-1)+1;
       int y=random.nextInt()*(squares.length-1)+1;
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
         for(int i=1;i<squares.length-1;i++)
           {
               for(int j=1;j<squares.length-1;j++)
               {
                   if(squares[i][j].IsMine())
                   {
                   for(int coordinat=0;coordinat<8;coordinat++)
                   {
                       int tempX=i+coordinator[coordinat][0];
                       int tempY=j+coordinator[coordinat][1];
                               squares[tempX][tempY].getSquareStatus().setValue(squares[tempX][tempY].getSquareStatus().getValue()+1);
                   }
                   }
               }
           }
        
    }
}
