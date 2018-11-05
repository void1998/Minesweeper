package programming3.pkg1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class NormalGame extends Game {
    public class DefaultRules extends GameRules 
    {

        @Override
        public int GetScoreChange(PlayerMove move) {
            String status = move.getSquare().getSquareStatus().getStatus();
            int value = move.getSquare().getSquareStatus().getValue();
                if(status .equals(Constants.MARK))
                {
                    if(value == 9)
                    {
                        return 5;
                    }
                    else
                    {
                        return -1;
                    }
                }
                else if(!status.equals(Constants.CLOSED) && !status.equals(Constants.MARKED))
                {
                    if(value==0)
                    {
                        return 10;
                    }
                    else if(value>=1 && value<=8)
                    {
                        return value;
                    }
                    else
                    {
                        return 0;
                    }
                }
            return 0;
        }
    
     /*public Player DecideNextPlayer(List<PlayerMove> moves) {
         throw new UnsupportedOperationException("Not supported yet.");
     }*/
        @Override
     public String ChangePlayerStatus(PlayerMove move)
     {
         if(move.getPlayer().getCurrentScore().getRealScore()<0)
         {
             return Constants.LOSER;
         }
         else if(move.getPlayer().getCurrentScore().getRealScore()>=0 && move.getPlayer().getCurrentScore().getRealScore()<10)
         {
             return Constants.IN_DANGER;
         }
         else if(move.getPlayer().getCurrentScore().getRealScore()>10)
         {
              return Constants.PLAYING;
         }
         /*else
         {
             return "winner";
            }*/
         return "Playing";
        }
    }
    
    @Override
    public void initGame(int playersNumber)
    {
        // doing the new players list
        players = new ArrayList<>();
        Score score;
        PlayerStatue playerStatue;
        Player current;
        for(int i=0;i<playersNumber;i++)
        {
            score = new NumiricScore();
            playerStatue = new PlayerStatue();
            current = new ConsolePlayer(score, playerStatue);
            setCurrentPlayer(current);
            players.add(current);
        }
        setCurrentRules(new DefaultRules());
        setMoves(new ArrayList<>());
        setGameStatus(Constants.NOT_STARTED);
        setCurrentPlayer(players.get(0));
    }
    
    @Override
    public boolean AcceptMove(PlayerMove move, Grid myGrid) 
    {
        if(move.getSquare().getX()>=1 && move.getSquare().getX()<=19)
        {
            if(move.getSquare().getY()>=1 && move.getSquare().getY()<=19)
            {
                String state = myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().getStatus();
                switch (move.getMove().getType()) {
                    case Constants.MARK:
                        if(state .equals(Constants.CLOSED) || state .equals(Constants.MARKED))
                        {
                            return true;
                        }   break;
                    case Constants.REVEAL:
                        if(state .equals(Constants.CLOSED))
                        {
                            return true;
                        }   break;
                }
            }
        }
        return false;
    }
    @Override
    public void ApplyPlayerMove(PlayerMove move,Grid myGrid)
    {
        String state = myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().getStatus();
        switch(move.getMove().getType())
        {
            case Constants.MARK:
            {
                if(state .equals(Constants.CLOSED))
                {
                    move.getSquare().getSquareStatus().setStatus(Constants.MARKED);
                    myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().setStatus(Constants.MARKED);
                }
                else if(state .equals(Constants.MARKED))
                {
                    move.getSquare().getSquareStatus().setStatus(Constants.CLOSED);
                    myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().setStatus(Constants.CLOSED);
                }
                break;
                
            }
            case Constants.REVEAL:
            {
                /*if(state == Constants.CLOSED)
                {*/
                    int value = move.getSquare().getSquareStatus().getValue();
                    if(value == 0)
                    {
                        move.getSquare().getSquareStatus().setStatus(Constants.OPENED_EMPTY);
                        myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().setStatus(Constants.OPENED_EMPTY);
                        DFS(move.getSquare().getX(),move.getSquare().getY(),myGrid);
                    }
                    else if(value>=1 && value<=8)
                    {
                        move.getSquare().getSquareStatus().setStatus(Constants.OPENED_NUMBER);
                        myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().setStatus(Constants.OPENED_NUMBER);
                    }
                    else if(value == 9)
                    {
                        move.getSquare().getSquareStatus().setStatus(Constants.OPENED_MINE);
                        myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().setStatus(Constants.OPENED_MINE);
                    }
                    break;
                /*}
                else
                {
                    return "Already Marked Or Opened";
                }*/
            }
        }
    }
    
    @Override
    public void Winner()
    {
        Player thePlayer = null;
        if(players.size()==1)
        {
            thePlayer=players.get(0);
            thePlayer.getCurrentStatue().setStatus(Constants.WINNER);
        }
        else
        {
            int winScore = -100000;
            for(int i=0;i<players.size();i++)
            {
                if(players.get(i).getCurrentScore().getRealScore()>winScore)
                {
                    thePlayer =  players.get(i);
                    winScore = thePlayer.getCurrentScore().getRealScore();
                }
            }
            thePlayer.getCurrentStatue().setStatus(Constants.WINNER);
            for(int i=0;i<players.size();i++)
            {
                if(!players.get(i).getCurrentStatue().getStatus().equals(Constants.WINNER))
                {
                    players.get(i).getCurrentStatue().setStatus(Constants.LOSER);
                }
            }
        }
    }
    @Override
    public void DFS(int x, int y, Grid myGrid) 
    {
        Integer[][] coordinator=new Integer[][]{{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        int tempX,tempY;
        int value;
        for(int coordinat=0;coordinat<8;coordinat++)
        {
            tempX=x+coordinator[coordinat][0];
            tempY=y+coordinator[coordinat][1];
            if(tempX >=1 && tempX<=myGrid.getHieght() && tempY >=1 && tempY<=myGrid.getWidth())
            {
                if(myGrid.getSquares()[tempX][tempY].getSquareStatus().getStatus().equals(Constants.CLOSED))
                {
                    value = myGrid.getSquares()[tempX][tempY].getSquareStatus().getValue();
                    if(value == 0)
                    {
                        myGrid.getSquares()[tempX][tempY].getSquareStatus().setStatus(Constants.OPENED_EMPTY);
                        DFS(tempX,tempY,myGrid);
                    }
                    else if(value>0 && value<9)
                    {
                        myGrid.getSquares()[tempX][tempY].getSquareStatus().setStatus(Constants.OPENED_NUMBER);
                    }
                }
            }
        }
    }
}
