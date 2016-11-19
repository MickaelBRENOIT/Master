/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttemperature1;
import org.eclipse.paho.client.mqttv3.*;
/**
 *
 * @author Mikael
 */
public class ClientTemperature1 {

    /***
     * 
     *      4 projets différents pour 4 clients différents :
     *          1. client 1 : lit les températures et les publie
     *          2. client 2 : lit la présence humaine dans la maison et publie
     *          3. client 3 : recoit les informations, contrôle et publie aux radiateurs
     *          4. client 4 : lit l'état recu par le client 3 et l'applique aux radiateurs
     *          
     */
    
    private static MqttClient cuisineThermometre1;
    private static MqttClient sejourThermometre1;
    private static MqttClient sejourThermometre2;
    
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
        
        cuisineThermometre1 = new MqttClient(broker, "cT1");
        cuisineThermometre1.connect(connOpts);
        
        sejourThermometre1 = new MqttClient(broker, "sT1");
        sejourThermometre1.connect(connOpts);
        
        sejourThermometre2 = new MqttClient(broker, "sT2");
        sejourThermometre2.connect(connOpts);
        
         message = new MqttMessage();
    }
    
    private static void disconnect() throws MqttException{
        cuisineThermometre1.disconnect();
        sejourThermometre1.disconnect();
        sejourThermometre2.disconnect();
    }
    
    private static int random(int Min, int Max){
        return (int) (Min + (Math.random() * (Max - Min)));
    }
    
    public static void main(String[] args) throws MqttException, InterruptedException {
        // TODO code application logic here
        init();
        
        int cT1, sT1, sT2;
               
        for(int i=0; i<10; i++){
            
            System.out.println("\n i="+ i + " : \n");
            
            cT1 = random(0, 30);
            System.out.println("random cT1 : "+cT1);
            message.setPayload(Integer.toString(cT1).getBytes());
            message.setQos(qos);
            cuisineThermometre1.publish(topicPersonnal+"cuisine/thermometre/ct1", message);
            
            Thread.sleep(1000);

            sT1 = random(0, 30);
            System.out.println("random sT1 : "+sT1);
            message.setPayload(Integer.toString(sT1).getBytes());
            message.setQos(qos);
            sejourThermometre1.publish(topicPersonnal+"sejour/thermometre/st1", message);

            Thread.sleep(1000);
            
            sT2 = random(0, 30);
            System.out.println("random sT2 : "+sT2);
            message.setPayload(Integer.toString(sT2).getBytes());
            message.setQos(qos);
            sejourThermometre2.publish(topicPersonnal+"sejour/thermometre/st2", message);
        
            Thread.sleep(1000);
        
        }

        disconnect();
    
    }
    
}
