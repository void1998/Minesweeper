/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3.pkg1;


/**
 *
 * @author void
 */
public class ConsoleView implements View {
    @Override
    public void Draw(Square squares[][])
    {
        char characters;
        System.out.print("    ");
        for (char i=65;i<84;i++)
        {
            characters=i;
            System.out.print(characters+"  ");
        }
        System.out.println();
        for(int i=1;i<20;i++)
        {
            if(i<10)
             System.out.print(i+"   ");
            else 
               System.out.print(i+"  "); 
            for(int j=1;j<20; j++)
            {
                switch (squares[i][j].getSquareStatus().getStatus()) {
                    case Constants.MARKED:
                        System.out.print("P");
                        break;
                    case Constants.CLOSED:
                        System.out.print("O");
                        break;
                    case Constants.OPENED_EMPTY:
                        System.out.print(" ");
                        break;
                    case Constants.OPENED_NUMBER:
                        System.out.print(squares[i][j].getSquareStatus().getValue());
                        break;
                    case Constants.OPENED_MINE:
                        System.out.print("B");
                        break;
                    default:
                        break;
                }
                System.out.print("  ");
            }
            System.out.println();
        }
    }
}
