/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientradiateur4;
import org.eclipse.paho.client.mqttv3.*;

public class ClientRadiateur4 {

    
    /***
     * 
     *      4 projets différents pour 4 clients différents :
     *          1. client 1 : lit les températures et les publie
     *          2. client 2 : lit la présence humaine dans la maison et publie
     *          3. client 3 : recoit les informations, contrôle et publie aux radiateurs
     *          4. client 4 : lit l'état recu par le client 3 et l'applique aux radiateurs
     *          
     */
    
    private static MqttClient cuisineRadiateur1;
    private static MqttClient cuisineRadiateur2;
    private static MqttClient sejourRadiateur1;
    
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
        
        cuisineRadiateur1 = new MqttClient(broker, "cR1");
        cuisineRadiateur1.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                System.out.println("\t\tRadiateur 1 cuisine - etat : "+new String(mm.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
            }
        });
        cuisineRadiateur1.connect(connOpts);
        cuisineRadiateur1.subscribe(topicPersonnal+"cuisine/radiateur/cr1", qos);
        
        cuisineRadiateur2 = new MqttClient(broker, "cR2");
        cuisineRadiateur2.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                System.out.println("\t\tRadiateur 2 cuisine - etat : "+new String(mm.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
            }
        });
        cuisineRadiateur2.connect(connOpts);
        cuisineRadiateur2.subscribe(topicPersonnal+"cuisine/radiateur/cr2", qos);
        
        sejourRadiateur1 = new MqttClient(broker, "sR1");
        sejourRadiateur1.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                System.out.println("\t\tRadiateur 1 sejour - etat : "+new String(mm.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
            }
        });
        sejourRadiateur1.connect(connOpts);
        sejourRadiateur1.subscribe(topicPersonnal+"sejour/radiateur/sr1", qos);
        
        message = new MqttMessage();
        
    }
    
    private static void disconnect() throws MqttException{
        cuisineRadiateur1.disconnect();
        cuisineRadiateur2.disconnect();
        sejourRadiateur1.disconnect();        
    }
    
    public static void main(String[] args) throws InterruptedException, MqttException {
        // TODO code application logic here
        init();
               
        for(int i=0; i<10; i++){
            
            System.out.println("\n i="+ i + " : \n");
            Thread.sleep(5000);
        
        }

        disconnect();
    }
    
}
