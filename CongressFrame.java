//cvfe CSP-CongressionalDebate.jar Congress *.class *.jpg
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
This component displays the main screen that has the seating chart, precedence chart, questioning chart, and timer. 
*/

public class CongressFrame extends JFrame implements MouseListener, ActionListener, KeyListener
{  

	//ints that are keeping track # of people, etc  
	private int people; 
	private int count;
	private int totalQ; 
	
	
	//coordinates the naming of the seating chart and records them for future use
	private ArrayList<JPanel> namePanel;
	private ArrayList<JLabel> labelList; 
	
	//takes input of name to change the seating chart 
	private JTextField input;
	
	//base splitting of panels
	private JPanel sc;
	private JPanel bottom;
	private JPanel mid; 
	private JPanel p1;
	private JPanel p2;
	private JPanel p3; 
	
	//splitting of base panels
	private JPanel t1;
	private JPanel t2;
	private JPanel text; 
	
	 
	//precedence track 
	private JList<String> prec; 
	private DefaultListModel<String> listModel;
	private JList<String> prec2; 
	private DefaultListModel<String> listModel3; 
	private JList<String> prec3; 
	private DefaultListModel<String> listModel2; 
	private ArrayList<String> queue; 
	
	//keeping track of questions
	private TreeMap<String, Integer> qTracker;
	private JList<String> qName; 
	private DefaultListModel<String> qNameList; 
	private JList<String> qNum; 
	private DefaultListModel<String> qNumList; 
		
	//splitting panel 1
	private JPanel p11;
	private JPanel p12;
	private JPanel p13;
	
	
	//splitting panel 2
	private JPanel p21; 
	private JPanel p22; 
	
	//menu 
	private JMenuBar menuBar;
	private JMenu menu;
	
	//launches the timer
	private JButton startBtn; 
	


   /**
    * Constructs the frame by creating the layout and adding the people, precedence chart, and timekeeping 
    */
   public CongressFrame(int num)
   {  

	  people = num; 
	  setTitle("At The Leisure Of: A Congress Manager");
	  menuBar = new JMenuBar();
	  menu = new JMenu("View Options");
	  menuBar.add(menu);
	  JMenuItem item = new JMenuItem("Reset Precedence");
	  JMenuItem item2 = new JMenuItem("Reset Questions"); 
	  JMenuItem item3= new JMenuItem("Reset Precedence & Questions");
	  JMenuItem item4= new JMenuItem("Undo (Precedence)");
	  JMenuItem item5 = new JMenuItem("Review the Round");
	  

	  item.addActionListener(this);
	  item2.addActionListener(this);
	  item3.addActionListener(this);
	  item4.addActionListener(this);
	  item5.addActionListener(this);
	  
	  menu.add(item);
	  menu.add(item2); 
	  menu.add(item3);
	  menu.add(item4); 
	  menu.add(item5); 	
	  	
	  setJMenuBar(menuBar);

	  
	  listModel = new DefaultListModel<>();
	  prec = new JList<>(listModel);
	  listModel2 = new DefaultListModel<>();
	  prec2 = new JList<>(listModel2);
	  listModel3 = new DefaultListModel<>();
	  prec3 = new JList<>(listModel3);
	  queue = new ArrayList<String>(); 
	  	
	  qNameList = new DefaultListModel<>();
	  qName = new JList<>(qNameList);
	  qNumList = new DefaultListModel<>();
	  qNum = new JList<>(qNumList);
	  
	  qTracker = new TreeMap<String, Integer>(); 
      count = 0; 
      
      setSize(800,800);
      setLayout(new GridLayout(2,1)); 
      
      namePanel = new ArrayList<JPanel>();
      labelList = new ArrayList<JLabel>();
      sc = new JPanel();
      sc.setLayout(new BorderLayout()); 
      
      bottom = new JPanel(); 
      text = new JPanel();
      t1 = new JPanel(); 
      t2 = new JPanel(); 
      p1 = new JPanel(); 
      p2 = new JPanel(); 
      p3 = new JPanel(); 

      input = new JTextField(10);
      
      text.add((new JLabel("Name a competitor")));
      text.add(input);
      
      
      //adding all the people panels for the seating chart 
      for(int i = 1; i < people+1; i++)
      {
      	JPanel jp = new JPanel();
      	jp.setLayout(new GridLayout(2,2,2,2)); 
      	JLabel label = (new JLabel("Person " + i));
      	jp.add(label);
      	labelList.add(label); 
      	jp.add((new JLabel("")));
      	
      	JButton s = new JButton("S"); 
      	JButton q = new JButton("Q"); 
      	s.addMouseListener(this); 
      	q.addMouseListener(this); 
      	jp.add(s); 
      	jp.add(q);
      	jp.setBorder(BorderFactory.createLineBorder(Color.black)); 
      	sc.add(jp, BorderLayout.CENTER);
      	
      	
      	//coloring the speech button
      	s.setBackground(new Color(172, 177, 239));
		s.setOpaque(true);
		s.setBorderPainted(false);
		
		//coloring the question button
		q.setBackground(new Color(204, 172, 239));
		q.setOpaque(true);
		q.setBorderPainted(false);
		
      	namePanel.add(jp);
      }
      
      
      
      sc.add(text,BorderLayout.SOUTH);
      bottom.setLayout(new GridLayout(1,3)); 
      bottom.add(p1); 
      bottom.add(p2); 
      bottom.add(p3);
      p11 = new JPanel(); 
      p12 = new JPanel();
      p13 = new JPanel(); 
      
      p21 = new JPanel();
      p22 = new JPanel(); 
      
     
      p1.setLayout(new GridLayout(1,3));
      p1.add(p11); 
      p1.add(p12); 
      p1.add(p13);
      
      p2.setLayout(new GridLayout(1,2));
      p2.add(p21); 
      p2.add(p22); 
      
      p11.setLayout(new BoxLayout(p11, BoxLayout.PAGE_AXIS));
      p12.setLayout(new BoxLayout(p12, BoxLayout.PAGE_AXIS));
      p13.setLayout(new BoxLayout(p13, BoxLayout.PAGE_AXIS));
      
      //setting borders and the colors of the panels 
	      p11.setBackground(new Color(147, 187, 209));
		  p11.setOpaque(true);
		  
		  p12.setBackground(new Color(147, 167, 209));
		  p12.setOpaque(true);
		  
		  p13.setBackground(new Color(147, 187, 209));
		  p13.setOpaque(true);
		  
		  
		  p21.setBackground(new Color(147, 167, 209));
		  p21.setOpaque(true);
		  
		  p22.setBackground(new Color(147, 187, 209));
		  p22.setOpaque(true);
		  
		  p3.setBackground(new Color(147, 167, 209));
		  p3.setOpaque(true);
		  
	      p21.setLayout(new BoxLayout(p21, BoxLayout.PAGE_AXIS));
	      p22.setLayout(new BoxLayout(p22, BoxLayout.PAGE_AXIS));
	      
	      p11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	      p12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	      p13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	      
	      p21.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	      p22.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	      
	      p1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2)); 
	      p2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2)); 
	      p3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3)); 
      
      
      //adding all the panels to the screen
      p11.add(prec); 
      p12.add(prec2); 
      p13.add(prec3); 
      
      p21.add(qName); 
      p22.add(qNum); 
    
      sc.setLayout(new GridLayout(5,5));
      input.addKeyListener(this);
      

      startBtn = new JButton("Launch Timer"); 
      startBtn.addMouseListener(this);
      p3.add(startBtn);
      
	     
      add(sc); 
      add(bottom);
      
      
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true); 
   }
   
   /**
      Takes action for buttons when the mouse presses the speech, question, and timer buttons
   */
   
   public void mousePressed(MouseEvent e)
   {  
    	 JButton temp = ((JButton) e.getSource()); 
    	 String in = temp.getText();
    	 JPanel panel = (JPanel) (temp.getParent());
    	 
    	 if(in.equals("S"))
    	 {
    	 	 int index = namePanel.indexOf(panel); 
    	 	 String n = labelList.get(index).getText();
    	 	 
    	 	 if(!listModel.contains(n) && !listModel.contains("*"+n))
    	 	 {
    	 		
    	 		listModel.addElement(n);
        	    prec = new JList<>(listModel);
        	    queue.add(n); 
    	 	 }
    	 	 
    	 	 else if(listModel.contains(n) && !listModel2.contains(n))
    	 	 {
    	 		listModel.set(listModel.indexOf(n), "*"+n); 
    	 		listModel2.addElement(n);
        	    prec2 = new JList<>(listModel2);
        	    queue.add(n); 
    	 	 }
    	 	 else if(listModel.contains("*"+n) && listModel2.contains(n) && !listModel3.contains(n))
    	 	 {
    	 		listModel2.set(listModel2.indexOf(n), "*"+n); 
    	 		listModel3.addElement(n);
        	    prec3 = new JList<>(listModel3);
        	    queue.add(n); 
    	 	 }
    	 		 
    	 }
    	 
    	 else if(in.equals("Q"))
    	 {
    		 int index = namePanel.indexOf(panel); 
    	 	 String n = labelList.get(index).getText();
    	 	 totalQ++; 
    	 	 if(qTracker.containsKey(n))
    	 	 {
    	 		qNumList.set(qNameList.indexOf(n), String.valueOf(qTracker.get(n)+1)); 
    	 		qTracker.put(n, qTracker.get(n)+1);
    	 		
    	 		qName = new JList<>(qNameList);
    	 		qNum = new JList<>(qNumList);
    	 	 }
    	 	 else 
    	 	 {
    	 		qNameList.addElement(n); 
    	 		qNumList.addElement(String.valueOf(1));
    	 		qTracker.put(n, 1);
    	 		
    	 		
    	 		qName = new JList<>(qNameList);
    	 		qNum = new JList<>(qNumList);
    	 	 }
    	 }
    	 
   			
   		else if(in.equals("Launch Timer"))
   		{
   			SpeechTimer timer = new SpeechTimer(); 
   			timer.setVisible(true); 
   		}
   			
 
    	 
   }
   

   // Do-nothing methods that are part of the MouseListener Interface
   public void mouseReleased(MouseEvent event) {}
   public void mouseClicked(MouseEvent event) {}
   public void mouseEntered(MouseEvent event) {}
   public void mouseExited(MouseEvent event) {}
   //Do-nothing methods that are part of the KeyListener Interface
   public void keyTyped(KeyEvent e) {}
   public void keyReleased(KeyEvent e) {}   
   
   /**
      Takes action for text field when a new name is entered. 
   */ 
   public void keyPressed(KeyEvent e) {
   	
   		if(e.getKeyCode() == KeyEvent.VK_ENTER)
   		{

   			if(count > labelList.size()-1)
   			{
   				count = 0; 
   			}
   			String in = (input).getText();
   			labelList.get(count).setText(String.valueOf(in));
   	   		count++;
   	   		
   	   		if(count <= people)
   	   		{
   	   			input.setText("");
   	   		}
   			
   		}
      }
      
   /**
      Takes action when a menu item is selected
   */
   public void actionPerformed(ActionEvent e)
   {
   		
   		if(e.getSource() instanceof JMenuItem)
   		{
   			String in2 = ((JMenuItem) e.getSource()).getText(); 
   	   		if(in2.equals("Reset Precedence"))
   	   		{
   	   			listModel.clear();
   	   			listModel2.clear();
   	   			listModel3.clear();
   	   			queue.clear(); 
   	   		}
   	   		
   	   		else if(in2.equals("Reset Questions"))
	   		{
	   			qNumList.clear();
	   			qNameList.clear();
	   			qTracker.clear();
	   			totalQ = 0; 
	   		}
   	   		
   	   		else if(in2.equals("Reset Precedence & Questions")) 
   	   		{
   	   			listModel.clear();
	   			listModel2.clear();
	   			listModel3.clear();
	   			
	   			qNumList.clear();
	   			qNameList.clear(); 
	   			qTracker.clear();
	   			queue.clear();
	   			totalQ = 0;  
   	   		}
   	   		
   	   		else if(in2.equals("Undo (Precedence)"))
   	   		{
   	   			String n = ""; 
   	   			if(queue.size() > 0)
   	   			{
   	   				 n = (String) queue.remove(queue.size()-1);
   	   			}
   	   			else 
   	   			{
   	   				JOptionPane.showMessageDialog(null, "Nothing to undo");
   	   			}
   	   			
   	   			if(listModel.contains(n))
   	   			{
   	   				listModel.remove(listModel.indexOf(n)); 
   	   				prec = new JList<>(listModel);
   	   				
   	   			}
   	   			
   	   			else if(listModel2.contains(n))
	   			{
	   				listModel2.remove(listModel2.indexOf(n));
	   				prec2 = new JList<>(listModel2);
	   				
	   				if(listModel.contains("*" +n))
   	   				{
   	   					listModel.set(listModel.indexOf("*" +n), n); 
   	   				}
	   			}
   	   			
   	   			else if(listModel3.contains(n))
	   			{
	   				listModel3.remove(listModel3.indexOf(n)); 
	   				prec3 = new JList<>(listModel3);
	   				
	   				if(listModel2.contains("*" +n))
   	   				{
   	   					listModel2.set(listModel2.indexOf("*" +n), n); 
   	   				}
	   			}
   	   		
   	   		}
   	   		
   	   		else if (in2.equals("Review the Round"))
   	   		{
   	   			int speech = listModel.size() + listModel2.size() + listModel3.size(); 
   	   			int question = listModel.size() + listModel2.size() + listModel3.size(); 
   	   			String s = "Number of Speeches: " + speech + "\n Number of Questions: " + totalQ; 
   	   			JOptionPane.showMessageDialog(null, s);
   	   		}
   	   		
   		}
   		
   		
	    
   		
   		
   }
  

  
  
} 
