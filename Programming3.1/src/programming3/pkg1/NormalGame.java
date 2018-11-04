package programming3.pkg1;

import java.util.ArrayList;
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
    public boolean AcceptMove(PlayerMove move) 
    {
        if(move.getSquare().getX()>=1 && move.getSquare().getX()<=19)
        {
            if(move.getSquare().getY()>=1 && move.getSquare().getY()<=19)
            {
                String state = move.getSquare().getSquareStatus().getStatus();
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
    public void ApplyPlayerMove(PlayerMove move)
    {
        String state = move.getSquare().getSquareStatus().getStatus();
        switch(move.getMove().getType())
        {
            case Constants.MARK:
            {
                if(state .equals(Constants.CLOSED))
                    move.getSquare().getSquareStatus().setStatus(Constants.MARKED);
                else if(state .equals(Constants.MARKED))
                    move.getSquare().getSquareStatus().setStatus(Constants.CLOSED);
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
                    }
                    else if(value>=1 && value<=8)
                    {
                        move.getSquare().getSquareStatus().setStatus(Constants.OPENED_NUMBER);
                    }
                    else if(value == 9)
                    {
                        move.getSquare().getSquareStatus().setStatus(Constants.OPENED_MINE);
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
    
    /*@Override
    public void Winner()
    {
        Player thePlayer;
        if(players.size()==1)
        {
            thePlayer=players.get(0);
            thePlayer.getCurrentStatue().setStatus(Constants.WINNER);
        }
        else
        {
            players.sort(this.currentStatue.realScore);
            for(int i=0;i<players.size();i++)
            {
                thePlayer = players.get(i);
                if(thePlayer.getCurrentScore().getRealScore()>winScore)
                {
                    
                }
            }
        }
    }*/
}
