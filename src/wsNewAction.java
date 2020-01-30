import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;


import java.awt.*;
import java.awt.event.*;

    public class wsNewAction extends AbstractAction {
        public wsNewAction(String text, ImageIcon icon,
                          String desc, Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            System.out.println("Action for New Game Action"+e.getSource());
        }
    }
