package controller;

import gnu.io.*;
import view.Fenetre;	// cette classe détecte automatiquement le port de l'arduino pour la suite

import java.util.Enumeration;

public class CommPortTest  {
	static String port="COM1";	// on utilise le port 1 par défaut

	
	
	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		CommPortTest.port = port;
	}
	 
	
	
	static SerialTest SerialTest2 = new SerialTest();
	
	
public static void main(String[] args) {
System.out.println(" Program Started!!! ");
Fenetre fenetre1 = new Fenetre();
CommPortIdentifier serialPortId;

Enumeration enumComm;

enumComm = CommPortIdentifier.getPortIdentifiers();

while(enumComm.hasMoreElements())
{
serialPortId = (CommPortIdentifier)enumComm.nextElement();
if(serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL)
{
System.out.println(serialPortId.getName());
port = serialPortId.getName();
}
}


System.out.println(" Program Finished Sucessfully ");

CommPortTest.setPort(port);

SerialTest2.initialize();
}




}
