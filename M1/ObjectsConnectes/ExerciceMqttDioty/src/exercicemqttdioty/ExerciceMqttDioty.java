/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicemqttdioty;
import org.eclipse.paho.client.mqttv3.*;
/**
 *
 * @author Mickael
 */
public class ExerciceMqttDioty {

    /***
     *      cuisine :   un thermomètre et deux radiateurs
     *      sejour :    deux thermomètres et un radiateur
     */
    
    private static MqttClient cuisineThermometre1;
    private static MqttClient cuisineRadiateur1;
    private static MqttClient cuisineRadiateur2;
    
    private static MqttClient sejourThermometre1;
    private static MqttClient sejourThermometre2;
    private static MqttClient sejourRadiateur1;
    
    private static MqttClient central;
    private static MqttMessage message;
    
    private static int tempSejour1, tempSejour2;
    
    private final static String broker = "tcp://mqtt.dioty.co:1883";
    private static MqttConnectOptions connOpts;
    private final static String topicPersonnal = "/brenoit.mickael@gmail.com/";
    private final static int qos = 0;
    
    private static void init() throws MqttException{
        
        tempSejour1=-1; tempSejour2=-1;
        
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
        
        central = new MqttClient(broker, "central");
        central.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                String temp = new String(mm.getPayload());
                int temperature = Integer.parseInt(temp);

                switch(string){
                    case topicPersonnal+"cuisine/thermometre/ct1":
                        if(temperature < 15){
                            message.setPayload("ON".getBytes());
                            message.setQos(qos);
                            cuisineThermometre1.publish(topicPersonnal+"cuisine/radiateur/cr1", message);
                            cuisineThermometre1.publish(topicPersonnal+"cuisine/radiateur/cr2", message);
                        }else{
                            message.setPayload("OFF".getBytes());
                            message.setQos(qos);
                            cuisineThermometre1.publish(topicPersonnal+"cuisine/radiateur/cr1", message);
                            cuisineThermometre1.publish(topicPersonnal+"cuisine/radiateur/cr2", message);
                        }
                        break;

                    case topicPersonnal+"sejour/thermometre/st1":
                        tempSejour1 = temperature;
                        if(tempSejour2 != -1){
                            if((tempSejour1 + tempSejour2)/2 < 15){
                                message.setPayload("ON".getBytes());
                                message.setQos(qos);
                                sejourThermometre1.publish(topicPersonnal+"sejour/radiateur/sr1", message);
                            }else{
                                message.setPayload("OFF".getBytes());
                                message.setQos(qos);
                                sejourThermometre1.publish(topicPersonnal+"sejour/radiateur/sr1", message);
                            }
                        }
                        break;

                    case topicPersonnal+"sejour/thermometre/st2":
                        tempSejour2 = temperature;
                        if(tempSejour1 != -1){
                            if((tempSejour1 + tempSejour2)/2 < 15){
                                message.setPayload("ON".getBytes());
                                message.setQos(qos);
                                sejourThermometre2.publish(topicPersonnal+"sejour/radiateur/sr1", message);
                            }else{
                                message.setPayload("OFF".getBytes());
                                message.setQos(qos);
                                sejourThermometre2.publish(topicPersonnal+"sejour/radiateur/sr1", message);
                            }
                        }
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
        central.subscribe(topicPersonnal+"+/thermometre/+", qos);
        
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
        cuisineRadiateur1.subscribe(topicPersonnal+"cuisine/radiateur/cr1");
        
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
        cuisineRadiateur2.subscribe(topicPersonnal+"cuisine/radiateur/cr2");
        
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
        sejourRadiateur1.subscribe(topicPersonnal+"sejour/radiateur/sr1");
        
        message = new MqttMessage();
        
    }
    
    private static void disconnect() throws MqttException{
        cuisineRadiateur1.disconnect();
        cuisineThermometre1.disconnect();
        cuisineRadiateur2.disconnect();
        
        sejourRadiateur1.disconnect();
        sejourThermometre1.disconnect();
        sejourThermometre2.disconnect();
        
        central.disconnect();
        
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
