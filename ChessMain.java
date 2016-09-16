import javax.swing.*;
import java.awt.*;
class ChessMain
{

  public static void main(String args[]) throws Exception
  {
     Thread.sleep(2000); 
    ChessGUI obj=new ChessGUI();
    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    obj.setSize(900,720);
    obj.setLocationRelativeTo(null);
    obj.setLayout(new CardLayout());
    obj.setTitle("Brain Battle : By Prajwal Singh (- (G+) prajwaloggy15@gmail.com)");
    obj.setVisible(true);
  }

}