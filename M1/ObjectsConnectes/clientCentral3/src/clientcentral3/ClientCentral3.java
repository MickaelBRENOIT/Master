/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcentral3;
import org.eclipse.paho.client.mqttv3.*;
/**
 *
 * @author Mikael
 */
public class ClientCentral3 {

    /***
     * 
     *      4 projets différents pour 4 clients différents :
     *          1. client 1 : lit les températures et les publie
     *          2. client 2 : lit la présence humaine dans la maison et publie
     *          3. client 3 : recoit les informations, contrôle et publie aux radiateurs
     *          4. client 4 : lit l'état recu par le client 3 et l'applique aux radiateurs
     *      Le chauffage est allumé si la température est inférieure à 18°
     *      et que la personnes est présente dans la maison.
     *          
     */
    
    private static MqttClient central;
    
    private static MqttMessage message;
    
    private final static String broker = "tcp://mqtt.dioty.co:1883";
    private static MqttConnectOptions connOpts;
    private final static String topicPersonnal = "/brenoit.mickael@gmail.com/";
    private final static int qos = 0;
    
    private static int tempSejour1, tempSejour2;
    private static String presence, etat;
    
    private static void init() throws MqttException{
        tempSejour1=-1; tempSejour2=-1;
        
        connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName("brenoit.mickael@gmail.com");
        connOpts.setPassword(new char[]{'d','5','a','9','9','9','a','9'});
        
        central = new MqttClient(broker, "central");
        central.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                String var = new String(mm.getPayload());
                int temperature = Integer.parseInt(var);

                System.out.println(">>> "+string);
                
                switch(string){
                    case topicPersonnal+"presence":
                        presence = var;
                        System.out.println("\t--> "+presence);
                        break;
                        
                    case topicPersonnal+"cuisine/thermometre/ct1":
                        if("oui".equals(presence)){
                            if(temperature < 18){
                                message.setPayload("ON".getBytes());
                                message.setQos(qos);
                            }else{
                                message.setPayload("OFF".getBytes());
                                message.setQos(qos);
                            }
                        }else{
                            message.setPayload("OFF".getBytes());
                            message.setQos(qos);
                        }
                        central.publish(topicPersonnal+"cuisine/radiateur/cr1", message);                   //Perdu
                        central.publish(topicPersonnal+"cuisine/radiateur/cr2", message);
                        break;

                    case topicPersonnal+"sejour/thermometre/st1":
                        tempSejour1 = temperature;
                        if("oui".equals(presence)){
                            if(tempSejour2 != -1){
                                if((tempSejour1 + tempSejour2)/2 < 15){
                                    message.setPayload("ON".getBytes());
                                    message.setQos(qos);
                                }else{
                                    message.setPayload("OFF".getBytes());
                                    message.setQos(qos);
                                }
                            }
                        }else{
                            message.setPayload("OFF".getBytes());
                            message.setQos(qos);
                        }
                        central.publish(topicPersonnal+"sejour/radiateur/sr1", message);
                        break;

                    case topicPersonnal+"sejour/thermometre/st2":
                        tempSejour2 = temperature;
                        if("oui".equals(presence)){
                            if(tempSejour1 != -1){
                                if((tempSejour1 + tempSejour2)/2 < 15){
                                    message.setPayload("ON".getBytes());
                                    message.setQos(qos);
                                }else{
                                    message.setPayload("OFF".getBytes());
                                    message.setQos(qos);
                                }
                            }
                        }else{
                            message.setPayload("OFF".getBytes());
                            message.setQos(qos);
                        }
                        central.publish(topicPersonnal+"sejour/radiateur/sr1", message);
                        break;

                    default:
                        System.out.println("Erreur inconnue, topic => "+string);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
            }
        });
        central.connect(connOpts);
        central.subscribe(topicPersonnal+"+/+/+", qos);
        
        message = new MqttMessage();
    }
    
    private static void disconnect() throws MqttException{
        central.disconnect();        
    }
    
    public static void main(String[] args) throws MqttException, InterruptedException {
        // TODO code application logic here
        // TODO code application logic here
        init();
               
        for(int i=0; i<10; i++){
            
            System.out.println("\n i="+ i + " : \n");
            Thread.sleep(5000);
        
        }

        disconnect();
    }
    
}
