package programming3.pkg1;

import java.util.ArrayList;
import java.util.List;

public class NormalGame extends Game {
    public class DefaultRules extends GameRules 
    {

        public int GetScoreChange(List<PlayerMove> moves) {
            PlayerMove move=moves.get(moves.size()-1);
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
     public String ChangePlayerStatus(PlayerMove move)
     {
         if(move.getPlayer().getCurrentScore()<0)
         {
             return "Loser";
         }
         else if(move.getPlayer().getCurrentScore()>=0 && move.getPlayer().getCurrentScore()<10)
         {
             return "in danger";
         }
         else if(move.getPlayer().getCurrentScore()>10)
         {
              return "currently playing";
         }
         /*else
         {
             return "winner";
            }*/
         return "Playing";
        }
    }
    
    public void initGame()
    {
       GameRules currentRules = new DefaultRules();
       Player currentPlayer = new Player();
       List <Player> players = new ArrayList<Player>();
       List <PlayerMove> moves = new ArrayList<PlayerMove>();
    }
    
    public boolean AcceptMove(PlayerMove move) 
    {
        if(move.getSquare().getX()>=1 && move.getSquare().getX()<=19)
        {
            if(move.getSquare().getY()>=1 && move.getSquare().getY()<=19)
            {
                String state = move.getSquare().getSquareStatus().getStatus();
                switch (move.getMove().getType()) {
                    case Constants.MARK:
                        if(state .equals(Constants.CLOSED))
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
    public void ApplyPlayerMove(PlayerMove move)
    {
        String state = move.getSquare().getSquareStatus().getStatus();
        switch(move.getMove().getType())
        {
            case Constants.MARK:
            {    
                move.getSquare().getSquareStatus().setStatus(Constants.MARK);
            }
            case Constants.REVEAL:
            {
                /*if(state == Constants.CLOSED)
                {*/
                    int value = move.getSquare().getSquareStatus().getValue();
                    if(value == 0)
                    {
                        move.getSquare().getSquareStatus().setStatus(Constants.OPENED_EMPTY);
                    }
                    else if(value>=1 && value<=8)
                    {
                        move.getSquare().getSquareStatus().setStatus(Constants.OPENED_NUMBER);
                    }
                    else if(value == 9)
                    {
                        move.getSquare().getSquareStatus().setStatus(Constants.OPENED_MINE);
                    }
                /*}
                else
                {
                    return "Already Marked Or Opened";
                }*/
            }
        }
    }
}