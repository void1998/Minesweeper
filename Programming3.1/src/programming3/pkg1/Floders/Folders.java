/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Floders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import programming3.pkg1.Grid;
import programming3.pkg1.UtilPackage.Constants;

/**
 *
 * @author ASUS
 */
public class Folders implements Serializable
{
    public static void quickSave(Grid myGrid) throws IOException
    {
        FileOutputStream f = new FileOutputStream("Saved Games/quickSave.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
        objectOutputStream.writeObject(myGrid);
    }
    
    public static void save(Grid myGrid) throws IOException
    {
        String folderPath;
        if(myGrid.getCurrentGame().getGameStatus().equals(Constants.FINISHED))
        {
            folderPath = "Saved Games/Finished Games/"+myGrid.getGameNumber()+".bin";
        }
        else
        {
            folderPath = "Saved Games/On Going Games/"+myGrid.getGameNumber()+".bin";
        }
        FileOutputStream f = new FileOutputStream(folderPath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
        objectOutputStream.writeObject(myGrid);
    }
    
    public static void writeNumber(int number) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileOutputStream f = new FileOutputStream("Saved Games/gameNumber.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
        objectOutputStream.writeObject(number);
    }
    
    public static Grid quickLoad() throws IOException, ClassNotFoundException
    {
        Grid myGrid;
        FileInputStream f = new FileInputStream("Saved Games/quickSave.bin");
        ObjectInputStream objectInputStream = new ObjectInputStream(f);
        myGrid = (Grid) objectInputStream.readObject();
        return myGrid;
    }
    
    public static Grid readFile(String folderPath) throws IOException, ClassNotFoundException
    {
        Grid myGrid;
        FileInputStream f = new FileInputStream(folderPath);
        ObjectInputStream objectInputStream = new ObjectInputStream(f);
        myGrid = (Grid) objectInputStream.readObject();
        return myGrid;
    }
    
    public static int readNumber() throws IOException, ClassNotFoundException
    {
        int number;
        FileInputStream f = new FileInputStream("Saved Games/gameNumber.bin");
        ObjectInputStream objectInputStream = new ObjectInputStream(f);
        number = (int) objectInputStream.readObject();
        return number;
    }
    
    public static List<Grid> getFinishedGrids() throws IOException
    {
        int number=0;
        List<Grid> grids = new ArrayList<>();
        Grid myGrid;
        String folderPath;
        try {
            number = readNumber();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(Folders.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("cannot open number file");
            number=0;
        }
        for(int i=0;i<number;i++)
        {
            try {
                folderPath = "Saved Games/Finished Games/"+i+".bin";
                File f = new File(folderPath);
                if(f.exists())
                {
                    myGrid = readFile(folderPath);
                    grids.add(myGrid);
                }
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(Folders.class.getName()).log(Level.SEVERE, null, ex);
                System.out.printf("cannot get the game with number: %d",i);
            }  
        }
        return grids;
    }
    
    public static List<Grid> getOnGoingGrids() throws IOException
    {
        int number=0;
        List<Grid> grids = new ArrayList<>();
        Grid myGrid;
        String folderPath;
        try {
            number = readNumber();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(Folders.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("cannot open number file");
            number=0;
        }
        for(int i=0;i<number;i++)
        {
            try {
                folderPath = "Saved Games/On Going Games/"+i+".bin";
                File f = new File(folderPath);
                if(f.exists())
                {
                    myGrid = readFile(folderPath);
                    grids.add(myGrid);
                }
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(Folders.class.getName()).log(Level.SEVERE, null, ex);
                System.out.printf("cannot get the game with number: %d",i);
            }  
        }
        return grids;
    }
}
