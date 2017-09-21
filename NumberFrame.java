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
This component displays the beginning screen, prompting you for the number of people in the room 
*/


public class NumberFrame extends JFrame implements ActionListener
{

	
	private int num; 
	private JTextField field;
	private  JLabel label;

	public NumberFrame()
	{
		
		setSize(400,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Welcome to ATLO: At The Leisure Of");
	   
	    JPanel panel = new JPanel();
	    
	    panel.setLayout(new GridLayout(3,1));
	    panel.setBackground(new Color(147, 167, 209));
	    panel.setOpaque(true);
	    
	    ImageIcon whatevs = new ImageIcon(getClass().getResource("gavel.jpg"));
	    panel.add(new JLabel(whatevs));

	    field = new JTextField(1);

	    label = new JLabel("How many people in your round? (Press Enter to Begin)");
	    label.setPreferredSize(new Dimension(1,1));
	    panel.add(label);
	    panel.add(field);
	    
	    add(panel); 
	    setVisible(true);
	    field.addActionListener(this);

	    
	}
	
	/**
      Paints the starting frame
   */  
	public void paint(Graphics g) {
	    
	    this.getContentPane().setBackground(new Color(33, 112, 130));
	}
	
	
	/**
      When enter is pressed, it takes the number entered and creates a Congress frame and also gets rid of itself
   */
	public void actionPerformed(ActionEvent e) {
		
		try{
			num = Integer.parseInt(((JTextField) e.getSource()).getText());
			this.setVisible(false);
			CongressFrame congress = new CongressFrame(num); 
			congress.setVisible(true);
		}
		catch(NumberFormatException err)
		{
			JOptionPane.showMessageDialog(null, "That isn't a number");
			field.setText("");
		}
			
			 
		
		
	}
	
}