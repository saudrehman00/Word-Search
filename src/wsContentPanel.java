import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*; 

import javax.swing.border.*; 


public class wsContentPanel extends JPanel {

	private int bSize=5;
      private Color backgroundcolor=Color.WHITE; //Color.lightGray;
      private Color defaultcolor;
      private ActionListener listener;
      private int GridSide=10;
      private Vector  mybuttonslist=new Vector();
      private Vector mywordslist=new Vector();
      private Vector selectedwords=new Vector();
      static final String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public wsContentPanel(int gridside, Color defaultcolor, ActionListener listener) {
		setBorder(new EmptyBorder(bSize, bSize, bSize, bSize));
		setBackground(backgroundcolor);
            GridSide=gridside;
            this.defaultcolor=defaultcolor;
            this.listener=listener;
            setLayout(new GridLayout(GridSide, GridSide));
            setWords();
           for (int i=0; i < GridSide*GridSide; i++) {
              wsButton mygridbutton=new wsButton(defaultcolor, " ", listener,i);               
              mybuttonslist.add(mygridbutton);
              this.add(mygridbutton);
            }
            redraw();
	}
      public void setButtonColor(Color newcolor, int buttonindex) {
           ((wsButton)mybuttonslist.get(buttonindex)).setNewColor(newcolor);
      }
      public void clearScreen() {
        for (int i=0; i < mybuttonslist.size(); i++) {
           ((wsButton)mybuttonslist.get(i)).setOriginalColor();
        }
      }
      private void setWords() {
			mywordslist.add(new String("ATEEB"));
			mywordslist.add(new String("CHANGE"));
			mywordslist.add(new String("CRITICAL"));
			mywordslist.add(new String("SMART"));
			mywordslist.add(new String("TASTY"));
			mywordslist.add(new String("VALUE"));
			mywordslist.add(new String("NAEEM"));
			mywordslist.add(new String("SAUD"));
			mywordslist.add(new String("NAVEED"));
			mywordslist.add(new String("MESSI"));
			mywordslist.add(new String("SOFIA"));
			mywordslist.add(new String("RONA"));
			mywordslist.add(new String("READ"));
			mywordslist.add(new String("TEST"));
			mywordslist.add(new String("BIKE"));
			mywordslist.add(new String("NEW"));
			mywordslist.add(new String("STAR"));
			mywordslist.add(new String("TOYS"));
			mywordslist.add(new String("BOY"));
			mywordslist.add(new String("GIRL"));
			mywordslist.add(new String("MOON"));
			mywordslist.add(new String("TREE"));
			mywordslist.add(new String("WING"));
			mywordslist.add(new String("TRY"));
			mywordslist.add(new String("ANT"));
			mywordslist.add(new String("BUG"));
			mywordslist.add(new String("WEED"));
			mywordslist.add(new String("WORK"));
			mywordslist.add(new String("BUS"));
			mywordslist.add(new String("ROAD"));
			mywordslist.add(new String("STOP"));
			mywordslist.add(new String("SMART"));
			mywordslist.add(new String("CANADA"));
			mywordslist.add(new String("CHICKEN"));
			mywordslist.add(new String("BURGER"));
     }

     public void redraw() {
       selectedwords.removeAllElements();
       for (int i=0; i < mybuttonslist.size(); i++) {
            wsButton mygridbutton=(wsButton)mybuttonslist.get(i);               
            mygridbutton.setButtonText(" ");
            mygridbutton.setOriginalColor();
       }
//       System.out.println("GridSide is "+GridSide);
       String newword="";
       for (int i=0; i < GridSide; i++) {
          boolean wordfound=false;
          while (!wordfound) {
             int x = new Double(Math.random()*mywordslist.size()).intValue();
             newword = (String)mywordslist.get(x);
             wordfound=true;
             for (int j=0; j < selectedwords.size(); j++) {
                 if (((String)selectedwords.get(j)).equals(newword)) {
                    wordfound = false;
                    break;
                 } 
             }
          }
//          System.out.println(newword);
          selectedwords.add(newword);
          boolean  invalidflag = true;
          int     wordlength = newword.length();
          int     Grid=GridSide*GridSide;
          int     starting = 0;
          int     ending = 0;
          int     pattern = 0;
          double  prevdiv=0;
          double  currdiv=0;
          int    columnval = 0;
          int    rowval = 0;
          while (invalidflag) {
             invalidflag=false;
             pattern = new Double(Math.random()*6+1).intValue();
             if (pattern==2) {
                pattern = GridSide -1;  //7
                columnval = new Double(wordlength+(Math.random()*(GridSide-wordlength))).intValue();
                rowval = new Double(Math.random() * (GridSide - wordlength)).intValue();
                
             } else if (pattern==3) {
                pattern = GridSide; //8;
                columnval = new Double(Math.random() * GridSide).intValue();
                rowval = new Double(Math.random() * (GridSide - wordlength)).intValue();
                //starting = new Double(Math.random()*(Grid - wordlength)*GridSide).intValue();
             } else if (pattern==4) {
                pattern = GridSide+1; //9;
                columnval = new Double(Math.random() * (GridSide - wordlength)).intValue();
                rowval = new Double(Math.random() * (GridSide - wordlength)).intValue();
                //starting = new Double(wordlength+(Math.random()*(GridSide-wordlength-1)) + ((Math.random() * (Grid-(wordlength+1)*GridSide)/GridSide))).intValue();
             } else if (pattern==5) {
            	 pattern = -GridSide+1; //-7;
            	 columnval = new Double(Math.random() * (GridSide - wordlength)).intValue();
            	 rowval = new Double(wordlength + (Math.random() * (GridSide - wordlength))).intValue();
                 //ending = new Double(wordlength+(Math.random()*(GridSide-wordlength-1)) + ((Math.random() * (Grid-(wordlength+1)*GridSide)/GridSide))).intValue();
                 //starting = ending - (wordlength * pattern);
             } else if (pattern==6) {
            	 pattern = -GridSide; //-8;
            	 //ending = new Double(Math.random()*(Grid - wordlength)*GridSide).intValue();
            	 //starting = ending - (wordlength * pattern);
            	 columnval = new Double(Math.random() * GridSide).intValue();
            	 rowval = new Double(wordlength + (Math.random() * (GridSide - wordlength))).intValue();
             }else {
                pattern=1;
                //starting = new Double(((Math.random() * GridSide) * GridSide) + (GridSide - wordlength)*Math.random()).intValue();
                columnval = new Double(Math.random() * (GridSide - wordlength)).intValue();
                rowval = new Double(Math.random() * GridSide).intValue();
             }
             if (columnval == 0) {
            	 columnval = 1;
             }
             if (rowval == 0) {
            	 rowval = 1;
             }
             starting =  rowval*GridSide + columnval;
             /*
             if (pattern < 0) {
            	 starting=new Double((2*(wordlength+1+Math.random()*(GridSide-wordlength-1)))*0.5*GridSide).intValue();
             } else {
                 starting=new Double(Math.random()*(Grid-(GridSide*wordlength))).intValue();
             }
             */
             if ((starting > Grid) || (starting <= 0)) {
             invalidflag = true;
             
             }
             
             /*
             prevdiv = Math.floor((starting)/GridSide);
  //           System.out.println("Starting "+starting+" Pattern "+pattern+ " word "+newword+" wordlength "+newword.length());
             for (int k=2; k <= wordlength; k++) {
                 currdiv=Math.floor((starting+(k-1)*pattern)/GridSide);
                 if (((Math.abs(currdiv-prevdiv) != 1) &&
                        (pattern != 1)) ||
                        ((Math.abs(currdiv-prevdiv) != 0) &&
                        (pattern == 1))) {
//                    System.out.println(i+"Invalid Flag st. "+wordlength+" "+starting+" k "+k+" "+pattern+" "+currdiv+" "+prevdiv+" "+GridSide);
                    invalidflag=true;
                    break;
                 }
                 prevdiv=currdiv;
             } */ /* end for */
             
           //  if (invalidflag) {
//                System.out.println("After For Loop....Invalid");
           //  } else {
 //               System.out.println("After For Loop...Valid");
            // }
             
             if (!invalidflag) {
                     ending=starting+(wordlength-1)*pattern;
//                     System.out.println("Starting "+starting+" Ending "+ending);
                     if ((ending < 0) || (ending > Grid))  {
//                        System.out.println("Ending is greater than the Grid "+ending+" "+Grid);
                        invalidflag=true;
                     } else {
                    	 /*
                         int k=0;
                         for (int n=starting; n<=ending;) {
                            String valuestring=((wsButton)(mybuttonslist.get(n))).getButtonText();
                            if (!valuestring.equals(" ") &&
                                !valuestring.equals(newword.substring(k, k+1))) {
                                invalidflag=true;
                                break;
                            }
                            k++;
                            n+=pattern;
                         } 
                         */
                    	 /* new logic */
                    	 int k=0;
                    	 int n=starting;
                    	 for (k=0; k < wordlength; k++) {
                    		 String valuestring=((wsButton)(mybuttonslist.get(n-1))).getButtonText();
                              if (!valuestring.equals(" ") &&
                                    !valuestring.equals(newword.substring(k, k+1))) {
                                    invalidflag=true;
                                    break;
                              }
                              n += pattern;
                    	 }
                    	 if ((n < 0 ) || (n > Grid)) {
                    		 invalidflag = true;
                    	 }
                         if (!invalidflag) {
                            n=starting;
                            for (k=0; k < wordlength; k++) {
                              ((wsButton)(mybuttonslist.get(n-1))).setButtonText(newword.substring(k, k+1));
                              n+=pattern;
                            }
                            System.out.println(newword+" "+pattern+" "+"Starting "+starting+" Ending "+ending);

                         }
                     }  /* end if ending > Grid */
                 } /* end if !invalidflag */
              } /* end while invalidflag */
       }  /* end for i<Grid */
       for (int i=0; i < GridSide*GridSide; i++) {
         String mychar = ((wsButton)(mybuttonslist.get(i))).getButtonText();
         if (mychar.equalsIgnoreCase(" ")) {
            int y= new Double(Math.floor(Math.random()*25)).intValue();
            mychar = alpha.substring(y, y+1);
            ((wsButton)(mybuttonslist.get(i))).setButtonText(mychar);
         }
       }
       for (int i=0; i <selectedwords.size(); i++) {
//          System.out.print("    "+selectedwords.get(i));
       }
     }  /* end public function redraw */
        
     public void setBackGroundColor(Color newcolor) {
        backgroundcolor = newcolor;
        setBackground(newcolor);
     }
     public Vector getSelectedWords() {
        return selectedwords;
     }
}


