/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Timerhelperspackage;

import static java.lang.Thread.sleep;
import java.util.TimerTask;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import programming3.pkg1.Grid;

/**
 *
 * @author CEC
 */
public class MoveTimer extends Thread{

    int t = 10;
    public static StringProperty ttText;
    public Label tempTimer = new Label();
    public StringProperty getTtTextProperty() {
        return ttText;
    }

    public void setTtText(String ttText) {
        this.ttText.set(ttText);
    }
    
    public String getTtText()
    {
        return ttText.get();
    }
     public MoveTimer()
    {
        ttText = new SimpleStringProperty(this,"Time","0");
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
            Integer temp = x;
            setTtText(temp.toString());
            tempTimer.setText(getTtText());
            System.out.println(getTtText());
            x--;
        }
        check();
        run();
        }
    public void check()
    {
       
    }
}
