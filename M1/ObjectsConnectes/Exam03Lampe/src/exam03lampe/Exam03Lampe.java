/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam03lampe;

import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author e1500727
 */
public class Exam03Lampe {

    private static MqttClient Lampe;
    private static MqttMessage message;
    
    private static void init() throws MqttException{
        Lampe = new MqttClient("tcp://localhost:1883", "lampe");
        Lampe.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                System.out.println("\tEtat - Lampe : "+new String(mm.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
            }
        });
        Lampe.connect();
        Lampe.subscribe("lampe");
        
        message = new MqttMessage();
    }
    
    public static void main(String[] args) throws MqttException {
       init();
    }
    
}
