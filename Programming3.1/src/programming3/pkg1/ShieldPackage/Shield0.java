/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.ShieldPackage;

/**
 *
 * @author void
 */
public class Shield0 extends Shield {
         protected int number;
    public Shield0()
    {
        this.number++;
        ShieldsNumber++;
        type=0;
        x=0;
        y=0;
    }
    
    @Override
    public int InteractWithScore(int scoreChange)
    {
        return 0;
    }
    
    @Override
    public void RemoveShield()
    {
        this.number--;
        ShieldsNumber--;
    }
}
