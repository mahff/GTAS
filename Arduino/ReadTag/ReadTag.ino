#include <SPI.h>
#include <PN532_SPI.h>
#include "PN532.h"

PN532_SPI pn532spi(SPI, 10);
PN532 nfc(pn532spi);

int valid = 5; 
int failed = 6;
int actif = 4;  

void setup(void) {
  Serial.begin(115200);
  nfc.begin();
  nfc.SAMConfig();
  pinMode(valid, OUTPUT); 
  pinMode(failed, OUTPUT); 
  pinMode(actif, OUTPUT); 
}


void readSerial(){
  if(Serial.available() > 0){
    int bin = Serial.read(); 
    char inChar = char (bin);
    if(inChar == 'V'  || inChar == 'v'){
      digitalWrite(valid, HIGH); 
      delay(1000); 
      digitalWrite(valid, LOW);
    }
    else{
      digitalWrite(failed, HIGH); 
      delay(1000); 
      digitalWrite(failed, LOW); 
    }
  }
}

void writeSerial(){
  uint8_t success;
  uint8_t uid[] = { 0, 0, 0, 0, 0, 0, 0 }; 
  uint8_t uidLength;
  success = nfc.readPassiveTargetID(PN532_MIFARE_ISO14443A, uid, &uidLength);
  
  if (success) {
    nfc.PrintHex(uid, uidLength);
  } 
}

void loop(void) {
   digitalWrite(actif, HIGH); 
   writeSerial(); 
   //readSerial(); 
}
