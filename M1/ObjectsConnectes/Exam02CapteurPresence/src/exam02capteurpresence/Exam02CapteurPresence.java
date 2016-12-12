/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam02capteurpresence;

import java.util.Random;
import org.eclipse.paho.client.mqttv3.*;
/**
 *
 * @author e1500727
 */
public class Exam02CapteurPresence {

    private static MqttClient capteurPresence;
    private static MqttMessage message;
    
    private static void init() throws MqttException{
        capteurPresence = new MqttClient("tcp://localhost:1883", "Presence");
        capteurPresence.connect();
        
        message = new MqttMessage();
    }
    
    /*private static void disconnect() throws MqttException{
        capteurLumiere.disconnect();
    }*/
    
    public static void main(String[] args) throws InterruptedException, MqttException {
        Random rand = new Random();
        boolean presence;
        
        init();
        
        while(true){
            Thread.sleep(2000);
            System.out.println("Nouveau passage : \n");
            //On généère une température aléatoire entre 0 et 30
            presence = rand.nextBoolean();
            message.setPayload(Boolean.toString(presence).getBytes());
            capteurPresence.publish("presence", message);
        }
        
        //disconnect();
    }
    
}
