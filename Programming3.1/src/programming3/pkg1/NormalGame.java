package programming3.pkg1;

import java.util.List;

public class NormalGame extends Game {
    public class DefaultRules extends GameRules 
    {

        public int GetScoreChange(List<PlayerMove> moves) {
            PlayerMove move=moves.get(moves.size()-1);
            String status = move.square.squareStatus.GetStatus();
            int value = move.square.squareStatus.value;
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
        }
    
     /*public Player DecideNextPlayer(List<PlayerMove> moves) {
         throw new UnsupportedOperationException("Not supported yet.");
     }*/
     public String ChangePlayerStatus(PlayerMove move)
     {
         if(move.player.currentScore.GetScore()<0)
         {
             return "Loser";
         }
         else if(move.player.currentScore.GetScore()>=0 && move.player.currentScore.GetScore()<10)
         {
             return "in danger";
         }
         else if(move.player.currentScore.GetScore()>10)
         {
              return "currently playing";
         }
         /*else
         {
             return "winner";
            }*/
        }
    }
    
    public boolean AcceptMove(PlayerMove move) 
    {
        if(move.square.getX()>=1 && move.square.getX()<=19)
        {
            if(move.square.getY()>=1 && move.square.getY()<=19)
            {
                String state = move.square.squareStatus.getStatus();
                if(move.move.getType() == Constants.MARK)
                {
                    if(state == Constants.CLOSED)
                    {
                        return true;
                    }
                }
                else if(move.move.getType() == Constants.OPEN)
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
        switch(move.move.getType())
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