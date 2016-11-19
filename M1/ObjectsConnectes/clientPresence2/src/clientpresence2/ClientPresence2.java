/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientpresence2;
import org.eclipse.paho.client.mqttv3.*;
/**
 *
 * @author Mikael
 */
public class ClientPresence2 {

    /***
     * 
     *      4 projets différents pour 4 clients différents :
     *          1. client 1 : lit les températures et les publie
     *          2. client 2 : lit la présence humaine dans la maison et publie
     *          3. client 3 : recoit les informations, contrôle et publie aux radiateurs
     *          4. client 4 : lit l'état recu par le client 3 et l'applique aux radiateurs
     *          
     */
    
    private static MqttClient presenceDansLaMaison;
    
    private static MqttMessage message;
    
    private final static String broker = "tcp://mqtt.dioty.co:1883";
    private static MqttConnectOptions connOpts;
    private final static String topicPersonnal = "/brenoit.mickael@gmail.com/";
    private final static int qos = 0;
    
    private static void init() throws MqttException{
        connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName("brenoit.mickael@gmail.com");
        connOpts.setPassword(new char[]{'d','5','a','9','9','9','a','9'});
        
        presenceDansLaMaison = new MqttClient(broker, "Presence");
        presenceDansLaMaison.connect(connOpts);
        
         message = new MqttMessage();
    }
    
    private static void disconnect() throws MqttException{
        presenceDansLaMaison.disconnect();
    }
    
    private static int random(int Min, int Max){
        return (int) (Min + (Math.random() * (Max - Min)));
    }
    
    public static void main(String[] args) throws MqttException, InterruptedException {
        // TODO code application logic here
        init();
        
        int randPresence;
        String presence;
               
        for(int i=0; i<10; i++){
            
            System.out.println("\n i="+ i + " : \n");
            
            randPresence = random(0, 2);
            System.out.println("random présence : "+randPresence);
            if(randPresence == 0)
                presence = "non";
            else
                presence = "oui";
            message.setPayload(presence.getBytes());
            message.setQos(qos);
            presenceDansLaMaison.publish(topicPersonnal+"presence", message);
            
            Thread.sleep(5000);
        
        }

        disconnect();
    
    }
    
}
