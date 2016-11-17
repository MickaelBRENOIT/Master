/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice2.mqtt;
import org.eclipse.paho.client.mqttv3.*;
/**
 *
 * @author Mickael
 */
public class Exercice2Mqtt {

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
    
    private static void init() throws MqttException{
        
        tempSejour1=-1; tempSejour2=-1;
        
        cuisineThermometre1 = new MqttClient("tcp://localhost:1883", "cT1");
        cuisineThermometre1.connect();
        
        sejourThermometre1 = new MqttClient("tcp://localhost:1883", "sT1");
        sejourThermometre1.connect();
        
        sejourThermometre2 = new MqttClient("tcp://localhost:1883", "sT2");
        sejourThermometre2.connect();
        
        central = new MqttClient("tcp://localhost:1883", "central");
        central.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                System.out.println("???>"+string);
                String temp = new String(mm.getPayload());
                int temperature = Integer.parseInt(temp);
                
                switch(string){
                    case "cuisine/thermometre/ct1":
                        if(temperature < 15){
                            System.out.println("112313213132123123123132132131321313212311312313131");
                            message.setPayload("ON".getBytes());
                            central.publish("cuisine/radiateur/cr1", message);
                            central.publish("cuisine/radiateur/cr2", message);
                        }else{
                            message.setPayload("OFF".getBytes());
                            central.publish("cuisine/radiateur/cr1", message);
                            central.publish("cuisine/radiateur/cr2", message);
                        }
                        break;
                        
                    case "sejour/thermometre/st1":
                        System.out.println("zfezefrtdrdyttufututfufuyfuyfufyufuyfuyfyufyufufufuyfu");
                        tempSejour1 = temperature;
                        if(tempSejour2 != -1){
                            if((tempSejour1 + tempSejour2)/2 < 15){
                                message.setPayload("ON".getBytes());
                                central.publish("sejour/radiateur/sr1", message);
                            }else{
                                message.setPayload("OFF".getBytes());
                                central.publish("sejour/radiateur/sr1", message);
                            }
                        }
                        break;
                        
                    case "sejour/thermometre/st2":
                        System.out.println("azerty");
                        tempSejour2 = temperature;
                        if(tempSejour1 != -1){
                            if((tempSejour1 + tempSejour2)/2 < 15){
                                message.setPayload("ON".getBytes());
                                central.publish("sejour/radiateur/sr1", message);
                            }else{
                                message.setPayload("OFF".getBytes());
                                central.publish("sejour/radiateur/sr1", message);
                            }
                        }
                        break;
                        
                    default:
                        System.out.println("nique ta mère");
 
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        central.connect();
        central.subscribe("+/thermometre/+");
        
        cuisineRadiateur1 = new MqttClient("tcp://localhost:1883", "cR1");
        cuisineRadiateur1.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                System.out.println("\t\tRadiateur 1 cuisine - etat : "+new String(mm.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        cuisineRadiateur1.connect();
        cuisineRadiateur1.subscribe("cuisine/radiateur/cr1");
        
        cuisineRadiateur2 = new MqttClient("tcp://localhost:1883", "cR2");
        cuisineRadiateur2.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                System.out.println("\t\tRadiateur 2 cuisine - etat : "+new String(mm.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        cuisineRadiateur2.connect();
        cuisineRadiateur2.subscribe("cuisine/radiateur/cr2");
        
        sejourRadiateur1 = new MqttClient("tcp://localhost:1883", "sR1");
        sejourRadiateur1.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable thrwbl) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void messageArrived(String string, MqttMessage mm) throws Exception {
                System.out.println("\t\tRadiateur 1 sejour - etat : "+new String(mm.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sejourRadiateur1.connect();
        sejourRadiateur1.subscribe("sejour/radiateur/sr1");
        
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
            cuisineThermometre1.publish("cuisine/thermometre/ct1", message);
            
            Thread.sleep(1000);

            sT1 = random(0, 30);
            System.out.println("random sT1 : "+sT1);
            message.setPayload(Integer.toString(sT1).getBytes());
            sejourThermometre1.publish("sejour/thermometre/st1", message);

            Thread.sleep(1000);
            
            sT2 = random(0, 30);
            System.out.println("random sT2 : "+sT2);
            message.setPayload(Integer.toString(sT2).getBytes());
            sejourThermometre2.publish("sejour/thermometre/st2", message);
        
            Thread.sleep(1000);
        
        }

        disconnect();
    }
    
}
