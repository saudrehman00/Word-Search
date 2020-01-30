import java.awt.Dimension;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*; 


public class wsToolBar extends JToolBar {

	private ActionListener listener;

	private JButton button1;
	
	private JTextField currentstatus;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date starttime = new Date();
	Date currenttime = new Date();
	Date endtime = new Date();

	public wsToolBar(ActionListener listener) {
		setFloatable(false);
		this.listener=listener;
		currentstatus = new JTextField();
        currentstatus.setPreferredSize(new Dimension(700,25));
        add(currentstatus);
		currentstatus.setText("Current Time:"+dateFormat.format(currenttime));
		button1=new JButton("New Game");
		button1.setActionCommand("NEW");
		button1.addActionListener(listener);
		button1.setToolTipText("New Game");
		add(button1);
//		button1=new JButton("BackGround Color");
//		button1.setActionCommand("GAMEBACKGROUND");
//		button1.addActionListener(listener);
//		button1.setToolTipText("Change Background Color");
//		add(button1);
//		button1=new JButton("Box Color");
//		button1.setActionCommand("GAMECOLOR");
//		button1.addActionListener(listener);
//		button1.setToolTipText("Change Color");
//		add(button1);
		button1=new JButton("Exit");
		button1.setActionCommand("EXIT");
		button1.addActionListener(listener);
		button1.setToolTipText("Exit Program");
		add(button1);
            
	}
	
	public void setStartTime() {
		starttime = new Date();
		currentstatus.setText("Start Time: "+dateFormat.format(starttime));
	}
	
	public void setEndTime() {
		long totalelapsed;
		endtime = new Date();
		totalelapsed = (endtime.getTime() - starttime.getTime()) / (60 * 1000); 
		currentstatus.setText("Start Time: "+dateFormat.format(starttime)+" End Time: "+endtime+" Total Points: "+totalelapsed*50);
		
	}

	
}


