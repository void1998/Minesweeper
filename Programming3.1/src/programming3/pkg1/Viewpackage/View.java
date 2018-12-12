/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1.Viewpackage;

import java.io.Serializable;
import programming3.pkg1.Square;
import programming3.pkg1.Square;

/**
 *
 * @author void
 */
public interface View extends Serializable{
    public abstract void Draw(Square grid[][]);
}
