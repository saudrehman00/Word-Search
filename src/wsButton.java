import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.border.*; 

public class wsButton extends JButton {

      private Color buttoncolor=Color.lightGray;
      private Color buttonfontcolor=Color.BLACK;
      private Color defaultcolor=Color.lightGray;
      private Color buttonpreviouscolor=Color.lightGray;
      private String myvalue;
      private int   myposition;
      private boolean buttonclickedstate=false;

	public wsButton(Color initialcolor, String buttontext, ActionListener listener, int buttonposition) {
//           this.buttoncolor=buttoncolor;
           this.defaultcolor=initialcolor;
           this.buttonpreviouscolor=initialcolor;
           this.setFont(new Font("Arial", Font.PLAIN, 20));
           this.myvalue = buttontext;
           this.setText(buttontext);
           this.myposition=buttonposition;
           this.setActionCommand(buttontext);
           this.addActionListener(listener);
           this.setBackground(initialcolor);
	}
      public void ChangeButtonColor(Color newcolor) {
           this.buttoncolor=newcolor;
           this.setBackground(newcolor);
      }
      public void setNewColor(Color newcolor) {
           this.buttonfontcolor=newcolor;
           this.setForeground(newcolor);
      }
      public void setOriginalColor() {
          this.ChangeButtonColor(this.defaultcolor);
          this.setNewColor(Color.BLACK);
      }
      public String getButtonText() {
         return myvalue;
      }
      public void setButtonText(String newtext) {
          this.myvalue = newtext;
          this.setActionCommand(newtext);
          this.setText(newtext);
      }
      public String toString() {
        return this.myvalue;
      }
      public int getButtonPosition() {
         return myposition;
      }
      public Color getButtonColor() {
          return this.buttonfontcolor;
      }
      public Color getButtonBackground() {
         return this.buttoncolor;
      }
      public Color getButtonPreviousColor() {
         return this.buttonpreviouscolor;
      }
      public boolean getButtonState() {
         return this.buttonclickedstate;
      }
      public void setButtonClicked(Color newcolor) {
          if (!this.buttonclickedstate) {
             this.buttonpreviouscolor=this.buttoncolor;
             this.ChangeButtonColor(newcolor);
             this.buttonclickedstate=true;
          } 
      }
      public void setButtonUnclicked() {
         if (this.buttonclickedstate) {
            this.ChangeButtonColor(buttonpreviouscolor);
             this.buttonclickedstate=false;
         }          
      }
      public void toggleButtonState(Color newcolor) {
         if (this.buttonclickedstate)
            setButtonUnclicked();
         else
            setButtonClicked(newcolor);
      }         
}


