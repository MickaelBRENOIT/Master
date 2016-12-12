/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam01capteurlumiere;

import java.util.Random;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1500727
 */
public class Exam01CapteurLumiere {
    
    private static MqttClient capteurLumiere;
    private static MqttMessage message;
    
    private static void init() throws MqttException{
        capteurLumiere = new MqttClient("tcp://localhost:1883", "Lumiere");
        capteurLumiere.connect();
        
        message = new MqttMessage();
    }
    
    /*private static void disconnect() throws MqttException{
        capteurLumiere.disconnect();
    }*/
    
    public static void main(String[] args) throws InterruptedException, MqttException {
        Random rand = new Random();
        int lumiere;
        init();
        
        while(true){
            Thread.sleep(2000);
            System.out.println("Nouveau passage : \n");
            //On généère une température aléatoire entre 0 et 30
            lumiere = rand.nextInt(100);
            message.setPayload(Integer.toString(lumiere).getBytes());
            capteurLumiere.publish("lumiere", message);
        }
        
        //disconnect();
    }
    
}
