package programming3.pkg1;

import java.util.List;

public class NormalGame extends Game {
    public class DefaultRules extends GameRules 
    {

        public int GetScoreChange(List<PlayerMove> moves) {
            PlayerMove move=moves.get(moves.size()-1);
            String status = move.getSquare().getSquareStatus().getStatus();
            int value = move.getSquare().getSquareStatus().getValue();
            if(status == "Marked")
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
            else if(status == "Opened")
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
    
    public boolean AcceptMove(PlayerMove move) 
    {
        if(move.getSquare().getX()>=1 && move.getSquare().getX()<=19)
        {
            if(move.getSquare().getY()>=1 && move.getSquare().getY()<=19)
            {
                String state = move.getSquare().getSquareStatus().getStatus();
                if(move.getMove().getType() == Constants.MARK)
                {
                    if(state == Constants.CLOSED)
                    {
                        return true;
                    }
                }
                else if(move.getMove().getType() == Constants.OPEN)
                {
                    if(state == Constants.CLOSED)
                    {
                        return true;
                    }
                }
            }
        }
        else
            return false;
    }
    public void ApplyPlayerMove(PlayerMove move)
    {
        String state = move.square.squareStatus.getStatus();
        switch(move.getMove().getType())
        {
            case Constants.MARK:
            {    
                move.square.squareStatus.setStatus(Constants.MARK);
            }
            case Constants.OPEN:
            {
                /*if(state == Constants.CLOSED)
                {*/
                    int value = move.square.squareStatus.getValue();
                    if(value == 0)
                    {
                        move.square.squareStatus.setStatus(Constants.OPENEDEMPTY);
                    }
                    else if(value>=1 && value<=8)
                    {
                        move.square.squareStatus.setStatus(Constants.OPENEDNUMBER);
                    }
                    else if(value == 9)
                    {
                        move.square.squareStatus.setStatus(Constants.OPENEDMINE);
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