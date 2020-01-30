import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*; 

import javax.swing.border.*; 


public class wsWordsPanel extends JPanel {

      private Color backgroundcolor=Color.lightGray;
      private Color defaultcolor;
      private ActionListener listener;
      private Vector  mybuttonslist=new Vector();
      private Vector mywordslist;
      
      private int totalwordsfound = 0;

	public wsWordsPanel(Vector words, Color defaultcolor, ActionListener listener) {
//		setBorder(new EmptyBorder(bSize, bSize, bSize, bSize));
		setBackground(backgroundcolor);
            this.defaultcolor=defaultcolor;
            this.listener=listener;
            this.mywordslist = words;
            this.totalwordsfound = 0;
            setLayout(new GridLayout(words.size(),1));
            for (int i=0; i < words.size(); i++) {
              wsButton mygridbutton=new wsButton(defaultcolor, (String)words.get(i), listener,i);               
              mybuttonslist.add(mygridbutton);
              this.add(mygridbutton);
            }
	}
      public void setButtonColor(Color newcolor, int buttonindex) {
           ((wsButton)mybuttonslist.get(buttonindex)).setNewColor(newcolor);
      }
      public void clearScreen() {
        for (int i=0; i < mybuttonslist.size(); i++) {
           ((wsButton)mybuttonslist.get(i)).setOriginalColor();
        }
      }
      public boolean searchword(String word, Color newcolor) {
        wsButton mywordbutton;
        for (int i=0; i < mywordslist.size(); i++) {
           if (word.equalsIgnoreCase((String)mywordslist.get(i))){
             mywordbutton =(wsButton)mybuttonslist.get(i);
             if (mywordbutton.getButtonBackground() == defaultcolor) {
                  mywordbutton.setNewColor(Color.YELLOW);
                  mywordbutton.ChangeButtonColor(newcolor);
                  totalwordsfound++;
                  return true;
             }
           }
        }
        return false;
      }
      
     public void redraw(Vector newwords) {
       mywordslist = newwords;
       totalwordsfound = 0;
       for (int i=0; i < mybuttonslist.size(); i++) {
            ((wsButton)mybuttonslist.get(i)).setOriginalColor();
            ((wsButton)mybuttonslist.get(i)).setButtonText((String)mywordslist.get(i));
       }
     }  /* end public function redraw */
        
     public void setBackGroundColor(Color newcolor) {
        backgroundcolor = newcolor;
        this.setBackground(newcolor);
     }
     
     public boolean allwordsfound() {
    	 return (totalwordsfound == mywordslist.size());
     }
}


