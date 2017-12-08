package view;

import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panneau extends JPanel {	// cette classe n'est pas utilisé mais conservée en cas de modification future
	

	
	
  public void paintComponent(Graphics g){
    //Vous verrez cette phrase chaque fois que la méthode sera invoquée
    System.out.println("Je suis exécutée !"); 
    //g.fillOval(20, 20, 75, 75);
    
    
  }               
  
  
  
  
  
}
