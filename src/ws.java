import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class ws extends JFrame implements ActionListener {
	static ArrayList words;
	static int totalwords;
	static int maxlength = 0;
	static int totalcombinations = 0;
	static double pattern = 0;
	static double starting = 0;
	static double ending = 0;
	static int wordlength = 0;
	static String word = "";
	static double prevdiv = 0;
	static double currdiv = 0;
	static int invalidflag = 0;
	static double gridside = 0;
	static int intgridside = 0;
	static ArrayList charvalues;
	static final double grid = 64;
	static final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Container container;
	static JPanel panel1;
	static Color defaultcolor;
	static String searchedword = "";
	static Vector mybuttons = new Vector();
	static Vector mywords = new Vector();

	static mywordobject myobject;
	static JButton newbutton;
	static Color mycolor;

	public ws(ArrayList words) {
		this.words = words;
		this.totalwords = words.size();
		this.charvalues = new ArrayList(new Double(grid).intValue());
		this.gridside = Math.floor(Math.sqrt(grid));
		this.intgridside = new Double(gridside).intValue();
		WinAdapter winAdapter = new WinAdapter();
		addWindowListener(winAdapter);

		for (int i = 1; i <= grid; i++) {
			this.charvalues.add(new String(""));
		}
		container = this.getContentPane();
		panel1 = new JPanel(new GridLayout(intgridside, intgridside));
		container.add(panel1);
		for (int i = 0; i < totalwords; i++) {
			word = (String) words.get(i);
			wordlength = word.length();
//       System.out.println("Value at "+i+" is "+word+" Length is "+wordlength);
			if (wordlength > maxlength)
				maxlength = wordlength;
			ending = 0;
			invalidflag = 1;
			while (invalidflag > 0) {
				starting = Math.rint(Math.random() * (grid - (gridside * wordlength)));
				if (starting == 0)
					starting = 1;
//         System.out.println("Random Value is "+starting);
				pattern = Math.rint(Math.random() * 4);
//         System.out.println("Pattern is "+pattern);
				if (pattern == 1.0) {
					pattern = 1.0;
				} else if (pattern == 2.0) {
					pattern = 7.0;
				} else if (pattern == 3.0) {
					pattern = 8.0;
				} else if (pattern == 4.0) {
					pattern = 9.0;
				} else
					pattern = 1;
				prevdiv = Math.floor((starting - 1) / gridside);
//         System.out.println("Pattern 2 is "+pattern);
				for (int j = 2; j <= wordlength; j++) {
					currdiv = Math.floor((starting - 1 + (j - 1) * pattern) / gridside);

					if (((Math.abs(currdiv - prevdiv) != 1) && (pattern != 1))
							|| ((Math.abs(currdiv - prevdiv) != 0) && (pattern == 1))) {
						invalidflag = 1;
//               System.out.println("Setting Invalid Flag pattern "+pattern+" j "+j+" Prev Div "+prevdiv+" Current Div "+currdiv);
						break;
					}
					invalidflag = 0;
					prevdiv = currdiv;
				}
				if (invalidflag == 0) {
					ending = starting + (wordlength - 1) * pattern;
//           System.out.println("Ending value inside is "+ending);
					if (ending > grid) {
						invalidflag = 1;
					} else {
						double n = starting;
						int k = 0;
						String valuestring;
						for (n = starting; n <= ending;) {
							valuestring = (String) charvalues.get(new Double(n - 1).intValue());
							if (!valuestring.equals("") && !valuestring.equalsIgnoreCase(word.substring(k, k + 1))) {
//                   System.out.println("Value String "+valuestring+" word character "+word.substring(k, k+1));
								invalidflag = 1;
								break;
							}
							k++;
							n = n + pattern;
						}
						if (invalidflag != 1) {
//                System.out.println("Starting for "+word+" is "+starting);
//                System.out.println("Ending for "+word+" is " +ending);
							k = 0;
							for (n = starting; n <= ending;) {
								charvalues.set(new Double(n - 1).intValue(), word.substring(k, k + 1));
//                  System.out.println("Setting Value at "+n+" Value "+word.substring(k,k+1));
								k++;
								n = n + pattern;
							}
							AddMyWord(word, starting, ending, pattern);
						}
					}
				}
			}
		}
		System.out.println("Maximum Length is " + maxlength);
		ButtonListener mylistener = new ButtonListener();

		int rem2 = new Double(gridside).intValue() - 1;
		for (int i = 0; i < grid; i++) {
			String mychar = (String) charvalues.get(i);
			if (mychar.equalsIgnoreCase("")) {
				int y = new Double(Math.floor(Math.random() * 25)).intValue();
				mychar = alpha.substring(y, y + 1);
			}
			int remainder = i - (new Double(Math.floor(i / gridside) * gridside).intValue());
			if (remainder == rem2) {
				System.out.println("     " + mychar);
				System.out.println("");
				System.out.println("");
			} else {
				System.out.print("     " + mychar);
			}
			JButton mybutton = new JButton(mychar);
			mybutton.addActionListener(mylistener);
			defaultcolor = mybutton.getBackground();
			panel1.add(mybutton);
		}
		this.pack();
		this.setVisible(true);
	}

	public static boolean Valueexists(String value) {
//    System.out.println("Searching for value "+value);
		for (int i = 0; i < totalwords; i++) {
			String currentvalue = (String) words.get(i);
			if (currentvalue.equalsIgnoreCase(value)) {
				System.out.println("Value Exists..." + value);
				return true;
			}
		}
		return false;
	}

	public static void ClearObjectList() {
		for (int i = 0; i < mybuttons.size(); i++) {
			myobject = (mywordobject) mybuttons.elementAt(i);
			newbutton = (JButton) myobject.getMyButton();
			mycolor = (Color) myobject.getMyColor();
			newbutton.setBackground(mycolor);
		}
		mybuttons.removeAllElements();
	}

	public static void AddObjectWord(JButton mysource) {
		mywordobject myobj = new mywordobject(mysource.getBackground(), mysource.getText(), mysource);
		mybuttons.add(myobj);
//    System.out.println("Adding Object "+mysource.getText());
	}

	public static void ChangeObjectColor() {
//    System.out.println("Total Buttons "+mybuttons.size());
		for (int i = 0; i < mybuttons.size(); i++) {
			myobject = (mywordobject) mybuttons.elementAt(i);
			newbutton = (JButton) myobject.getMyButton();
			mycolor = (Color) myobject.getMyColor();
			if (mycolor.equals(defaultcolor)) {
				newbutton.setBackground(Color.GREEN);
				myobject.setMyColor(Color.GREEN);
//         System.out.println("Setting color to Green "+i+newbutton.getText());
			} else if (mycolor.equals(Color.CYAN)) {
//         System.out.println("Setting color to Green "+i+newbutton.getText());
				newbutton.setBackground(Color.GREEN);
				myobject.setMyColor(Color.GREEN);
			} else if (mycolor.equals(Color.GREEN)) {
				newbutton.setBackground(Color.RED);
				myobject.setMyColor(Color.RED);
//         System.out.println("Setting color to Red "+i+newbutton.getText());
			} else {
				newbutton.setBackground(Color.BLUE);
				myobject.setMyColor(Color.BLUE);
//         System.out.println("Setting color to Blue "+i+newbutton.getText());
			}
//       System.out.println("Value of i is "+i);
		}
		mybuttons.removeAllElements();
	}

	public void AddMyWord(String wordtext, double starting, double ending, double pattern) {
		myworditem myword = new myworditem(wordtext, starting, ending, pattern);
		mywords.add(myword);
	}

	class myworditem {
		String wordtext;
		double starting;
		double ending;
		double pattern;
		boolean found;

		myworditem(String wordtext, double starting, double ending, double pattern) {
			this.wordtext = wordtext;
			this.starting = starting;
			this.ending = ending;
			this.pattern = pattern;
			this.found = false;
		}

		void setFound(boolean found) {
			this.found = found;
		}

		boolean isFound() {
			return this.found;
		}

		String getWordText() {
			return wordtext;
		}

		double getPattern() {
			return pattern;
		}
	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton myobject = (JButton) e.getSource();
			if (myobject.getBackground().equals(Color.CYAN)) {
				myobject.setBackground(defaultcolor);
				searchedword = "";
				ClearObjectList();
			} else {
				AddObjectWord(myobject);
				myobject.setBackground(Color.CYAN);
				searchedword = searchedword + myobject.getText();
				if (Valueexists(searchedword)) {
					ChangeObjectColor();
					searchedword = "";
				}

			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	private class WinAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public static void main(String args[]) {
		ArrayList mywords = new ArrayList(10);
//     mycoins.add(new Integer(1));
		mywords.add(new String("READ"));
		mywords.add(new String("TEST"));
		mywords.add(new String("CAR"));
//     mywords.add(new String("VALUES"));
		mywords.add(new String("NEW"));
		mywords.add(new String("STAR"));
		mywords.add(new String("TOYS"));
		mywords.add(new String("BOY"));
		mywords.add(new String("GIRL"));
//     mywords.add(new String("MOTHER"));

		ws testsearch = new ws(mywords);

//     testsearch.display_all();
		System.out.println("");
		for (int i = 0; i < mywords.size(); i++) {
			System.out.print("  " + (String) mywords.get(i));
		}
	}
}
