/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Floders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import programming3.pkg1.Grid;

/**
 *
 * @author ASUS
 */
public class Folders implements Serializable
{
    public static void quickSave(Grid myGrid) throws IOException
    {
        FileOutputStream f = new FileOutputStream("quickSave.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
        objectOutputStream.writeObject(myGrid);
    }
    
    public static void save(Grid myGrid) throws IOException
    {
        String folderPath = myGrid.getGameNumber()+".bin";
        FileOutputStream f = new FileOutputStream(folderPath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
        objectOutputStream.writeObject(myGrid);
    }
    
    public static void writeNumber(int number) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileOutputStream f = new FileOutputStream("gameNumber.bin");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
        objectOutputStream.writeObject(number);
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
        int number=-1;
        FileInputStream f = new FileInputStream("gameNumber.bin");
        ObjectInputStream objectInputStream = new ObjectInputStream(f);
        number = (int) objectInputStream.readObject();
        return number;
    }
}
