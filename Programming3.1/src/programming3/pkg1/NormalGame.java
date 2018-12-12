package programming3.pkg1;

import programming3.pkg1.UtilPackage.Constants;
import programming3.pkg1.NumiricScore;
import programming3.pkg1.Score;
import programming3.pkg1.UtilPackage.GridInterface;
import java.util.ArrayList;
import java.util.List;
import programming3.pkg1.Game;
import programming3.pkg1.GameTime.GameTime;
import programming3.pkg1.PlayerMove;
import programming3.pkg1.ShieldPackage.Shield;
import programming3.pkg1.ShieldPackage.Shield100;
import programming3.pkg1.ShieldPackage.Shield200;
import programming3.pkg1.ShieldPackage.Shield50;

public class NormalGame extends Game implements GridInterface{
   static Grid currentGrid=new Grid();  
   
    public class DefaultRules extends GameRules 
    {

        @Override
        public int GetScoreChange(PlayerMove move) {
            String status = move.getSquare().getSquareStatus().getStatus();
            int value = move.getSquare().getSquareStatus().getValue();
                if(status .equals(Constants.MARKED))
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
                    else if(value ==9)
                    {
                        int size = getCurrentPlayer().shields.size();
                        if(size!=0)
                        {
                            int Change = getCurrentPlayer().shields.get(size-1).InteractWithScore(-50);
                            getCurrentPlayer().shields.get(size-1).RemoveShield();
                            getCurrentPlayer().shields.remove(size-1);
                            return Change;
                        }
                        else
                            return -50;
                    }
                }
            return 0;
        }
    
        @Override
        public Player DecideNextPlayer(Player currentPlayer) 
        {
            int playerNum;
            playerNum = (currentPlayer.playerNumber+1)%getPlayersNumber();
            return players.get(playerNum);
            
        }
        
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
    public void initGame(int playersNumber, int isAuto, int numberOfShieldsForPlayer)
    {
        // doing the new players list
        gameTime = new GameTime();
        players = new ArrayList<>();
        Score score;
        PlayerStatue playerStatue;
        String name;
        Player current;
        setPlayersNumber(playersNumber);
        for(int i=0;i<playersNumber;i++)
        {
            score = new NumiricScore();
            playerStatue = new PlayerStatue();
            name = "Player " + (i+1);
            current = new ConsolePlayer(score, playerStatue, name, i);
            //adding shield to the player
            int shields=numberOfShieldsForPlayer, count=3,shieldtype;
            while(count>0)
            {
                shieldtype=shields/count;
                shields=shields-shieldtype;
                for(int j=0;j<shieldtype;j++)
                {
                    if(count==3)
                    {
                        Shield shield = new Shield200();
                        current.shields.add(shield);
                    }
                    else if(count ==2)
                    {
                        Shield shield = new Shield100();
                        current.shields.add(shield);
                    }
                    else if(count ==1)
                    {
                        Shield shield = new Shield50();
                        current.shields.add(shield);
                    }
                }
                count--;
            }
            players.add(current);
        }
        if(isAuto == 1)
        {
            score = new NumiricScore();
            playerStatue = new PlayerStatue();
            name = "Auto Player";
            current = new EasyPlayer(score, playerStatue, name,(GridInterface)this, playersNumber);
            setCurrentPlayer(current);
            players.add(current);
            setPlayersNumber(getPlayersNumber()+1);
        } 
        setCurrentRules(new DefaultRules());
        setMoves(new ArrayList<>());
        setGameStatus(Constants.NOT_STARTED);
        setCurrentPlayer(players.get(0));
    }
    
    @Override
    public String AcceptMove(PlayerMove move, Grid myGrid) 
    {
        if(move.getSquare().getX()>=1 && move.getSquare().getX()<=myGrid.getHieght())
        {
            if(move.getSquare().getY()>=1 && move.getSquare().getY()<=myGrid.getWidth())
            {
                String state = myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().getStatus();
                switch (move.getMove().getType()) {
                    case Constants.MARK:
                        if(state .equals(Constants.CLOSED) || state .equals(Constants.MARKED))
                        {
                            return Constants.TRUE;
                        }
                        else
                            return "you can't mark an opened square";
                    case Constants.REVEAL:
                        if(state .equals(Constants.CLOSED))
                        {
                            return Constants.TRUE;
                        }   
                        else if(state .equals(Constants.MARKED))
                        {
                            return "you can't open a marked square";
                        }
                        else
                        {
                            return "you can't open an opened square";
                        }
                }
            }
            return "out of width exception";
        }
        return "out of hieght eception";
    }
    @Override
    public void ApplyPlayerMove(PlayerMove move,Grid myGrid)
    {
        //PlayerMove move = moves.get(moves.size()-1);
        String state = myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().getStatus();
        switch(move.getMove().getType())
        {
            case Constants.MARK:
            {
                switch (state) 
                {
                    case Constants.CLOSED:
                        move.getSquare().getSquareStatus().setStatus(Constants.MARKED);
                        myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().setStatus(Constants.MARKED);
                        break;
                    case Constants.MARKED:
                        move.getSquare().getSquareStatus().setStatus(Constants.CLOSED);
                        myGrid.getSquares()[move.getSquare().getX()][move.getSquare().getY()].getSquareStatus().setStatus(Constants.CLOSED);
                        break;
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
        currentGrid=myGrid;
    }
    
       @Override
    public Grid getGrid() {
        return currentGrid;
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
    
    @Override
    public String checkGame(Grid myGrid)
    {
        String state;
        String gameState = Constants.WINNER;
        int value;
        boolean check = true;
        for(int i=1;i<myGrid.getHieght();i++)
        {
            for(int j=1;j<myGrid.getWidth();j++)
            {
                state = myGrid.getSquares()[i][j].getSquareStatus().getStatus();
                value = myGrid.getSquares()[i][j].getSquareStatus().getValue();
                if((state.equals(Constants.CLOSED)&& value != 9) || (state.equals(Constants.MARKED) && value != 9))
                {
                    check = false;
                }
            }
        }
        if(check == false)
        {
            for(int i=1;i<myGrid.getHieght();i++)
            {
                for(int j=1;j<myGrid.getWidth();j++)
                {
                    state = myGrid.getSquares()[i][j].getSquareStatus().getStatus();
                    if(state.equals(Constants.OPENED_MINE))
                    {
                        gameState = Constants.LOSER;
                        break;
                    }
                    if(state.equals(Constants.CLOSED))
                    {
                        gameState = Constants.ON_GOING;
                    }
                }
                if(gameState.equals(Constants.LOSER))
                    break;
            }
        }
        if(gameState.equals(Constants.WINNER) || check == true)
        {
            myGrid.getCurrentGame().setGameStatus(Constants.FINISHED);
        }
        this.Winner();
        return gameState;
    }
}
