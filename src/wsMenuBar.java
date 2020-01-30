import java.awt.event.*;
import javax.swing.*;

public class wsMenuBar extends JMenuBar {

	private ActionListener listener;

	private JMenu menu1;
	private JMenuItem item1;
	protected Action newgame, exitgame, gamebackground, gamecolor;

	public wsMenuBar(JFrame frame, ActionListener listener) {
		this.listener = listener;

		newgame = new wsNewAction("NEW", createNavigationIcon("tbsave"), "New Game", new Integer(KeyEvent.VK_N));
		exitgame = new wsExitAction("EXIT", createNavigationIcon("tbcancel"), "Exit", new Integer(KeyEvent.VK_E));
//            gamebackground = new GameBackGroundAction("GAMEBACKGROUND"
//                                     ,createNavigationIcon("tbdown")
//                                     ,"Back Ground"
//                                     ,new Integer(KeyEvent.VK_B));
//            gamecolor = new GameColorAction("GAMECOLOR"
//                                           ,createNavigationIcon("tbup")
//                                           ,"Grid"
//                                           , new Integer(KeyEvent.VK_C));

		menu1 = new JMenu("Game");
		item1 = new JMenuItem(newgame);
		item1.addActionListener(listener);
		menu1.add(item1);
		item1 = new JMenuItem(exitgame);
		item1.addActionListener(listener);
		menu1.add(item1);
//            item1 = new JMenuItem(gamebackground);
//            menu1.add(item1);
//            item1 = new JMenuItem(gamecolor);
//            item1.setIcon(null);
//            menu1.add(item1);

		menu1.getInputMap().put(KeyStroke.getKeyStroke("F10"), "NEW");
		menu1.getActionMap().put("NEW", newgame);
		menu1.getInputMap().put(KeyStroke.getKeyStroke("F5"), "EXIT");
		menu1.getActionMap().put("EXIT", exitgame);

		add(menu1);

	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createNavigationIcon(String imageName) {
		String imgLocation = "icons/" + imageName + ".ico";
		java.net.URL imageURL = wsMenuBar.class.getResource(imgLocation);

		if (imageURL == null) {
//            System.err.println("Resource not found: "
//                               + imgLocation);
			return null;
		} else {
			return new ImageIcon(imageURL);
		}
	}

}
