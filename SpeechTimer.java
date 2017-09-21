import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Arrays;
import java.util.Date;
import java.util.TreeMap;
import java.util.ArrayList;
/**
   This component displays the timer that is launched by Congress Frame
*/

public class SpeechTimer extends JFrame implements ActionListener
{

	private Timer time; 
	private JLabel label; 
	private JLabel gavel; 
	private JPanel panel; 
	private int secCount; 
	private JButton startBtn; 
	private JButton stopBtn; 
	private JButton restartBtn; 
	
	public SpeechTimer()
	{
		this.setTitle("Timer for Speeches/Items");
		setSize(300,300);
		
		secCount = 0; 
		panel = new JPanel(); 
		panel.setLayout(new GridLayout(5,1));
		startBtn = new JButton("Start Timer"); 
		stopBtn = new JButton("Stop Timer");
		restartBtn = new JButton("Reset Timer");
		
		stopBtn.setBackground(new Color(255, 145, 150));
		stopBtn.setOpaque(true);
		stopBtn.setBorderPainted(false);
		
		
		startBtn.setBackground(new Color(159, 255, 145));
		startBtn.setOpaque(true);
		startBtn.setBorderPainted(false);
		
		restartBtn.setBackground(new Color(147, 167, 209));
		restartBtn.setOpaque(true);
		restartBtn.setBorderPainted(false);
				
		label = new JLabel("00:00");
		
		startBtn.addActionListener(this);
		stopBtn.addActionListener(this);
		restartBtn.addActionListener(this);
		
		panel.add(startBtn); 
		panel.add(stopBtn); 
		panel.add(restartBtn); 
		panel.add(label); 

		add(panel);
		Font f = label.getFont(); 
		label.setFont(new Font(f.getName(), f.getStyle(), f.getSize()+50));
		time = new Timer(1000, this);
		setVisible(true); 
	}
	/**
      Takes action for buttons when the respective timer elements are pressed
   */
	public void actionPerformed(ActionEvent e) {
		
		secCount++; 
	
		if(e.getSource() instanceof JButton)
		{
			String in2 = ((JButton) e.getSource()).getText(); 
			
			if(in2.equals("Start Timer"))
			{
				time.start(); 
			}
			else if(in2.equals("Stop Timer"))
			{
				time.stop();
				secCount--; 
			}
			else if (in2.equals("Reset Timer"))
			{
				time.stop();
				secCount = 0;
				
				label.setText("00:00");	
				
			}
		}
		
		int min = secCount/60; 
		int sec = secCount%60; 
		String sMin = min + ""; 
		String sSec = sec + ""; 
		
		if(sMin.length() == 1)
			sMin = "0" + sMin; 
		
		if(sSec.length() == 1)
			sSec = "0" + sSec; 
				
		label.setText(sMin+ ":" + sSec);
		
	}
	
}