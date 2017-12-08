
#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <DHT_U.h>  //bibliothèques que l'on inclut

#define DHTPIN 9     // le define permet de remplacer des valeurs du programmes par celles définis ici.


#define DHTTYPE DHT22   

DHT dht(DHTPIN, DHTTYPE);   // déclare le type de capteur dht d'humidité


#define ThermistorPIN 0 // Définit le pin du thermistor
double temp;
double Resistance;
double Tension;
double pd;
double tempSeuil=24;
int isCooling=0;
double humidity;
int save=0;
int condensationAlarm=0;
double magnus=0;
void setup(){   // Définit toutes les variables

  Serial.println("DHTxx test!");

  dht.begin();

  pinMode(3, OUTPUT); // définit les bornes d'activation 
  digitalWrite(3, LOW);
 

  pinMode(7, OUTPUT); // définit les bornes de la thermistance
  digitalWrite(7, HIGH);
  

Serial.begin(9600);
  
}

void loop(){

 save=Serial.read();  // lit le port série


  if (save==1){
      // si la touche + de l'interface est appuyée, la température de seuil augmente
    tempSeuil=tempSeuil+1;
  }
  else if (save==2){
     // si la touche - de l'interface est appuyée, la température de seuil diminue
    tempSeuil=tempSeuil-1;
  }



if (magnus >= Resistance){  //si la valeur de magnus est supérieure à la résistance, active l'alarme de condensation
  condensationAlarm=1;
}
else{
  condensationAlarm=0;
}

Serial.print("condensationAlarm/");
Serial.print(condensationAlarm);  // affiche l'alarme de condensation dans le port série
Serial.print("?condensationAlarm");

Serial.print("tempSeuil/");
Serial.print(tempSeuil);  // affiche la valeur de seuil de température dans le port série
Serial.print("?tempSeuil");

if (Resistance > tempSeuil+2){  // active ou désactive le ventilateur selon la température et la valeur de seuil

  digitalWrite(3, HIGH); 
  isCooling=1; 
  
}
if (Resistance < tempSeuil){

  
  digitalWrite(3, LOW);
  isCooling=0;
  
}





temp = analogRead(ThermistorPIN); //convertit la valeur de la tension de la résistance en température

Resistance = 10000.0/(1023.0/temp-1);//conversion analogique
Resistance = 1/(0.0027355 + 0.000272172 * log(Resistance/600) -0.00000074691 * 3 * log(Resistance/600)); //Steinhardt
Resistance = Resistance -262 ; //Kelvin en degrees

Serial.print("Temperature/");
Serial.print(Resistance); //affiche la température dans le port série
Serial.print("?Temperature");



Serial.println("magnus");
float K = (((17.27)*(float)Resistance) / (237.7+ (float)Resistance)) + log(humidity/100); // calcule la valeur de Magnus
float PdR = 237.7*K/(17.7-K);
Serial.println(PdR);
magnus=PdR;



  float h = dht.readHumidity();

  if (isnan(h)) {
    Serial.println("Impossible de calculer la valeur du capteur!");  
    return;
  }

  Serial.println("");
  Serial.print("Humidity/");
  Serial.print(h);  // affiche la valeur de l'humidité dans le port série
  Serial.print("?Humidity");

  humidity=h;

}










