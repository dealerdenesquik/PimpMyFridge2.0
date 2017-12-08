package view;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import org.jfree.chart.ChartPanel;

import controller.SerialTest;

public class Fenetre extends JFrame implements ActionListener {
	
	JButton bouton1 = new JButton("+");
	JButton bouton2 = new JButton("-");
	public static JLabel label1 = new JLabel("Test");
	public static JLabel label2 = new JLabel("Test");
	public static JLabel label3 = new JLabel("Test");
	public static JLabel label4 = new JLabel("Test");
	public static graph graph1 = new graph();
	public static ChartPanel chartPanel1;
	
  public Fenetre(){       // instancie les boutons et les blocs de textes ainsi que le graphique
	
	chartPanel1 = graph1.trucDraw();
	this.add(chartPanel1);
	//chartPanel1.setSize(10, 10);
	//this.setResizable(false);
    this.setTitle("Pimp my fridge");
    this.setSize(300, 400);
    this.setLocationRelativeTo(null);               
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(chartPanel1);
    this.getContentPane().add(bouton1);
    this.getContentPane().add(bouton2);
    this.getContentPane().add(label1);
    this.getContentPane().add(label2);
    this.getContentPane().add(label3);
    this.getContentPane().add(label4);
    this.setVisible(true);
    
    setLayout(null);
    Border border = BorderFactory.createLineBorder(Color.black);
    
    label1.setOpaque(true);
    label1.setBounds(new Rectangle(new Point(100, 300), label1.getPreferredSize()));
    label1.setForeground(Color.red);
    label1.setSize(200, 25);
    label1.setBorder(border);
    
    label2.setOpaque(true);
    label2.setBounds(new Rectangle(new Point(100, 278), label2.getPreferredSize()));
    label2.setForeground(Color.red);
    label2.setSize(200, 25);
    label2.setBorder(border);
    
    label3.setOpaque(true);
    label3.setBounds(new Rectangle(new Point(100, 256), label3.getPreferredSize()));
    label3.setForeground(Color.red);
    label3.setSize(200, 25);
    label3.setBorder(border);
    
    label4.setOpaque(true);
    label4.setBounds(new Rectangle(new Point(100, 234), label4.getPreferredSize()));
    label4.setForeground(Color.red);
    label4.setSize(200, 25);
    label4.setBorder(border);	// instancie chaque bloc texte
    
    bouton1.addActionListener(this);

    bouton2.addActionListener(this);
    
  }     
  
  
  public void actionPerformed(ActionEvent arg0) {

	  if(arg0.getSource() == bouton1){
		  System.out.println("+");
		  SerialTest.setArduinoSendVar(1);
		  System.out.println(SerialTest.getArduinoSendVar());	// change une variable quand on appuie sur un bouton
	  }

	  


	  if(arg0.getSource() == bouton2){
		  System.out.println("-");
		  SerialTest.setArduinoSendVar(2);
		  System.out.println(SerialTest.getArduinoSendVar());
	  }


	}
  
  
  
}
