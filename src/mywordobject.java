import javax.swing.*;
import java.awt.*;

 public class mywordobject {
    Color    mycurrcolor;
    String   mytext;
    JButton  mysource;

    public mywordobject(Color thecolor, String thetext, JButton thesource ) {
      this.mycurrcolor=thecolor;
      this.mytext=thetext;
      this.mysource=thesource;
    }
    public Color getMyColor() {
        return mycurrcolor;
    }
    public JButton getMyButton() {
      return mysource;
    }
    public void setMyColor(Color mycolor) {
      this.mycurrcolor=mycolor;
    }
 }
