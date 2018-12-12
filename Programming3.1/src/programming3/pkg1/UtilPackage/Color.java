package programming3.pkg1.UtilPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Color implements Serializable{
      private static int GUIplayerNumber=1;
      private final int standerdNumber=20;
      ////////////////////////////
      private static int consoleplayerNumber=0;
      private final List<String> colorList=Arrays.asList(".",",",";","~",":","^","`","-","*","'");
    
    
    public List<Integer> getGUIPlayerColor()
    {
          List<Integer> colorNumber=new ArrayList<>();
        colorNumber.add(standerdNumber*GUIplayerNumber);
        colorNumber.add(standerdNumber*GUIplayerNumber);
        colorNumber.add(standerdNumber*GUIplayerNumber);
        GUIplayerNumber++;
        return colorNumber;
    }
    
    
    public String getConsolePlayerColor()
    {
        return colorList.get(consoleplayerNumber++);
    }
}

