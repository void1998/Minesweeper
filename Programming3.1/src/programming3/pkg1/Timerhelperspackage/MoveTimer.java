/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Timerhelperspackage;

import static java.lang.Thread.sleep;
import java.util.TimerTask;
import javafx.scene.text.Text;
import programming3.pkg1.Grid;

/**
 *
 * @author CEC
 */
public class MoveTimer extends Thread{

    int t = 10;
    Text ttText = new Text();
     public MoveTimer()
    {
        
    }
    
    public int getT()
    {
        return t;
    }
    public void setT(int t)
    {
        this.t = t;
    }
    
    @Override
    public void run()
    {
        int x = t;
        while(x>0)
        {
            try{
                sleep(1000);
            } catch (InterruptedException ex)
            {
            }
            System.out.println(""+x);
            ttText.setText(""+x);
            x--;
        }
        check();
        run();
        }
    public void check()
    {
       
    }
}
