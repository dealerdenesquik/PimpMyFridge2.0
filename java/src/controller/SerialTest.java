package controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

import view.Fenetre;


public class SerialTest implements SerialPortEventListener {

	static int arduinoSendVar=0;
	
	public static int getArduinoSendVar() {
		return arduinoSendVar;
	}
	
	
	public static void setArduinoSendVar(int arduinoSendVar) {
		SerialTest.arduinoSendVar = arduinoSendVar;
	}





SerialPort serialPort;
/** Le port que l'on utilise. */
private static final String PORT_NAMES[] = {"/dev/tty.usbserial-A9007UX1", // Mac OS X
"/dev/ttyUSB0", // Linux
CommPortTest.getPort(), // Windows
};

private BufferedReader input;
private OutputStream output;
private static final int TIME_OUT = 2000;
private static final int DATA_RATE = 9600;
String read;
double test=0;


public void initialize() {
	
	PORT_NAMES[2]=CommPortTest.getPort();
	
CommPortIdentifier portId = null;
Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

//trouves une instance du port.
while (portEnum.hasMoreElements()) {
CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
for (String portName : PORT_NAMES) {
if (currPortId.getName().equals(portName)) {
portId = currPortId;
break;
}
}
}
if (portId == null) {
System.out.println(" Impossible de trouver le port. ");
return;
}

try {
serialPort = (SerialPort) portId.open(this.getClass().getName(),
TIME_OUT);
serialPort.setSerialPortParams(DATA_RATE,
SerialPort.DATABITS_8,
SerialPort.STOPBITS_1,
SerialPort.PARITY_NONE);

// démarre le stream
input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
output = serialPort.getOutputStream();

serialPort.addEventListener(this);
serialPort.notifyOnDataAvailable(true);
} catch (Exception e) {
System.err.println(e.toString());
}



}
public synchronized void close() {
if (serialPort != null) {
serialPort.removeEventListener();
serialPort.close();
}
}

public synchronized void serialEvent(SerialPortEvent oEvent) {

	
	
if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
try {
String inputLine=null;
if (input.ready()) {
inputLine = input.readLine();

String [] chunks = inputLine.split(" , ");

System.out.println(inputLine);
System.out.println(chunks[0] + " \t " + chunks[1] + " \t " + chunks[2] + " \t ");




}

} catch (Exception e) {
System.err.println(e.toString());
}





}


try {
	read = input.readLine();
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

String read1 = read.substring(read.lastIndexOf("tempSeuil/")+10 , read.indexOf("?tempSeuil"));

Fenetre.label1.setText("temp de seuil: "+read1+" °C");

String read2 = read.substring(read.lastIndexOf("Temperature/")+12 , read.indexOf("?Temperature"));

Fenetre.label2.setText("temperature: "+read2+" °C");

String read3 = read.substring(read.lastIndexOf("Humidity/")+9 , read.indexOf("?Humidity"));

Fenetre.label3.setText("humidite: "+read3+" %");

String read4 = read.substring(read.lastIndexOf("condensationAlarm/")+18 , read.indexOf("?condensationAlarm"));

//Attribue les différentes valeurs à des blocs de texte dans l'interface

float temp = Float.parseFloat(read2);

int alarm = Integer.parseInt(read4);


if (alarm==0){
	Fenetre.label4.setText("pas de risque de condensation");
}
else {
	Fenetre.label4.setText("RISQUE DE CONDENSATION!!!");
}

	test=test+0.1;
	Fenetre.graph1.tempIn.add(test,temp);



try {
	

	
	
	output.write(SerialTest.getArduinoSendVar());
	//output.flush();
	SerialTest.setArduinoSendVar(0);
	System.out.println(SerialTest.getArduinoSendVar());
	
	
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	System.out.println("exception error");
	
}




// Ignore all the other eventTypes, but you should consider the other ones.

}



public static void main(String[] args) throws Exception {
SerialTest main = new SerialTest();
main.initialize();
Thread t=new Thread() {
public void run() {
//the following line will keep this app alive for 1000 seconds,
//waiting for events to occur and responding to them (printing incoming messages to console).
try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
}
};
t.start();
System.out.println(" Started ");



}
}
