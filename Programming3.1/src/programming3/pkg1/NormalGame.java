package programming3.pkg1;

import java.util.ArrayList;
import java.util.List;

public class NormalGame extends Game {
    public class DefaultRules extends GameRules 
    {

        @Override
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
        @Override
     public String ChangePlayerStatus(PlayerMove move)
     {
         if(move.getPlayer().getCurrentScore().getScore()<0)
         {
             return Constants.LOSER;
         }
         else if(move.getPlayer().getCurrentScore()>=0 && move.getPlayer().getCurrentScore()<10)
         {
             return Constants.IN_DANGER;
         }
         else if(move.getPlayer().getCurrentScore()>10)
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
    public void initGame()
    {
       setCurrentRules(new DefaultRules());
       Score score = new NumiricScore();
       PlayerStatue playerStatue = new PlayerStatue();
       setCurrentPlayer(new ConsolPlayer(score, playerStatue));
       setPlayers(new ArrayList<>());
       setMoves(new ArrayList<>());
       setGameStatus(Constants.NOT_STARTED);
    }
    
    @Override
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
    @Override
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