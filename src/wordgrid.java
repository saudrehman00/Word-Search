import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Vector;

public class wordgrid extends JFrame implements ActionListener {
	private JPanel contentPane;

	private wsMenuBar menuBar;
	private wsToolBar toolBar;
//	private ColorStatusBar statusBar;

	private wsContentPanel contentPanel;
	private wsWordsPanel wordsPanel;

	private String currentword = "";
	private Vector wordsvector = new Vector();
	private int lastposition = -1;
	private int pattern = 0;
	private boolean patternOK = true;
	private Color wordcolor = Color.ORANGE;
	private Color newcolor = Color.RED;

	public wordgrid() {
		setTitle("Word Maze");

		WinAdapter winAdapter = new WinAdapter();
		addWindowListener(winAdapter);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPanel = new wsContentPanel(16, Color.WHITE, this);
		contentPanel.setOpaque(true);
		contentPane.add("Center", contentPanel);

		wordsPanel = new wsWordsPanel(contentPanel.getSelectedWords(), Color.lightGray, this);

		contentPane.add("East", wordsPanel);
		menuBar = new wsMenuBar(this, this);
		getRootPane().setJMenuBar(menuBar);

		toolBar = new wsToolBar(this);
		JPanel toolBarP = new JPanel();
		toolBarP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBarP.add(toolBar);
		toolBarP.setBorder(new LineBorder(Color.gray));
		contentPane.add("North", toolBarP);

//		statusBar=new TestStatusBar();
//		statusBar.setBorder(new LineBorder(Color.gray));
//		contentPane.add("South", statusBar);

		pack();

		setVisible(true);

	}

	private void removeword() {
		wsButton thisbutton;
		for (int w = 0; w < wordsvector.size(); w++) {
			((wsButton) wordsvector.get(w)).setButtonUnclicked();
		}
		wordsvector.removeAllElements();
		currentword = "";
		lastposition = -1;
		patternOK = true;
	}

	private void changewordcolor(Color newcolor) {
		wsButton mybutton;
		for (int w = 0; w < wordsvector.size(); w++) {
			mybutton = (wsButton) wordsvector.get(w);
			mybutton.setButtonUnclicked();
			if (mybutton.getButtonColor() != Color.YELLOW) {
				mybutton.setNewColor(Color.YELLOW);
			} else {
				mybutton.setNewColor(Color.CYAN);
			}
			if (mybutton.getButtonBackground() == newcolor) {
				mybutton.ChangeButtonColor(wordcolor);
			} else
				mybutton.ChangeButtonColor(newcolor);
		}
	}

	private void setButtonOn(wsButton currentbutton) {
		currentword = currentword.concat(currentbutton.getActionCommand());
		if (currentword.length() <= 1) {
			pattern = 0;
			patternOK = true;
		} else if (currentword.length() == 2) {
			pattern = currentbutton.getButtonPosition() - lastposition;
			patternOK = true;
		} else {
			if ((currentbutton.getButtonPosition() - lastposition) != pattern)
				patternOK = false;
			else
				patternOK = true;
		}
		lastposition = currentbutton.getButtonPosition();
		wordsvector.add(currentbutton);
		if (patternOK) {
			currentbutton.setButtonClicked(Color.CYAN);
//           if (currentbutton.getButtonBackground()==Color.RED) {
//               currentbutton.ChangeButtonColor(Color.YELLOW);
//           } else {
//               currentbutton.ChangeButtonColor(Color.CYAN);
//           }
		}
	}

	private void setButtonOff(wsButton currentbutton) {
		currentword = currentword.substring(0, currentword.length() - 1);
		if (currentword.length() <= 0) {
			removeword();
		} else {
			wordsvector.removeElementAt(wordsvector.size() - 1);
			if (wordsvector.size() > 0) {
				lastposition = ((wsButton) wordsvector.get(wordsvector.size() - 1)).getButtonPosition();
			} else {
				lastposition = -1;
			}
			currentbutton.setButtonUnclicked();

//            if (currentbutton.getButtonBackground()==Color.CYAN) {
//               System.out.println("In Cyan Setting Default Color...");
//               currentbutton.setOriginalColor();
//            } else if (currentbutton.getButtonBackground()==Color.YELLOW) {
//               currentbutton.ChangeButtonColor(Color.RED);
//            }
		}
	}

	private void toggleButton(wsButton currentbutton) {
		boolean buttonstate = currentbutton.getButtonState();
		if (buttonstate) {
			setButtonOff(currentbutton);
		} else
			setButtonOn(currentbutton);
//        currentbutton.toggleButtonState(Color.MAGENTA);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
//                System.out.println(command);
		if ((command.equals("Exit Program")) // menu bar
				|| (command.equals("EXIT"))) // tool bar
		{
			System.exit(0);
		} else if (command.equals("GAMEBACKGROUND")) {
//                System.out.println(command);
		} else if (command.equals("NEW")) {
			contentPanel.redraw();
			wordsPanel.redraw(contentPanel.getSelectedWords());
			currentword = "";
			toolBar.setStartTime();
			this.pack();
		} else if (command.equals("GAMECOLOR")) {
//                System.out.println(command);
		} else {
			wsButton currentbutton = (wsButton) e.getSource();
			if (command.length() == 1) {
				if (currentbutton.getButtonPosition() == lastposition) {
					toggleButton(currentbutton);
				} else {
					setButtonOn(currentbutton);
				}
//                 System.out.println("Last Position is "+lastposition+" Button Color is "+currentbutton.getButtonBackground());
//                 System.out.println("Word is "+ currentword);
				if (patternOK) {
					if (wordsPanel.searchword(currentword, newcolor)) {
						Color mynewcolor = Color.MAGENTA;
						while ((mynewcolor == Color.CYAN) || (mynewcolor == Color.MAGENTA)
								|| (mynewcolor == Color.lightGray)) {
							mynewcolor = new Color(new Double(Math.random() * 255).intValue(),
									new Double(Math.random() * 255).intValue(),
									new Double(Math.random() * 255).intValue());
//                         System.out.println("In words color...");
						}
						changewordcolor(newcolor);
						newcolor = mynewcolor;
						wordsvector.removeAllElements();
						currentword = "";
						lastposition = -1;
						if (wordsPanel.allwordsfound()) {
							toolBar.setEndTime();
						}
					} else {
//                      ((wsButton)e.getSource()).ChangeButtonColor(Color.CYAN);
//                       wordsvector.add(e.getSource());
					}
				} else {
					removeword();
				}
			}
		}
	}

	public static void main(String args[]) {
		new wordgrid();
	}

	private class WinAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

}
