/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1;

import programming3.pkg1.Score;

/**
 *
 * @author void
 */
public class NumiricScore extends Score {
    NumiricScore()
    {
        this.score = 0;
    }
    @Override
     public  Object scoreType()
     {
         return this.score ;
     }
}
