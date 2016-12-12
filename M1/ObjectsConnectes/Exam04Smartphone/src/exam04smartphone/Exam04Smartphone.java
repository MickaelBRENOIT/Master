/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam04smartphone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author e1500727
 */
public class Exam04Smartphone {

    
    //Afficher l’état de la lumière --- get
    private static void displayStateLight() throws IOException 
    {
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        String url = "http://localhost:4567/etatLumiere";
        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(false); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println("\tReponse Body | lumiere : "+responseBody);
            JSONObject obj = new JSONObject(responseBody);
            Boolean stateLight = obj.getBoolean("stateLight");
            System.out.println("Lumiere :" + stateLight);
        }
    }
    
    //Forcer l’allumage de la lumière --- post
    private static void forceLightOnLight() throws IOException 
    {   
        String charset = "UTF-8";
        String url = "http://localhost:4567/forcerAllumage";
        String query = String.format("forceLight=%s", "ON");
        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
        
        try (OutputStream output = connection.getOutputStream()) {
            output.write(query.getBytes(charset));
        }
        
        // début problème
        InputStream response = connection.getInputStream();
        // fin problème
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        boolean quitProgram=false;
        Scanner in = new Scanner(System.in);
        do
        {
            System.out.println("Le menu:");
            System.out.println("\t1 -> Afficher l’état de la lumière");
            System.out.println("\t2 -> Forcer l’allumage de la lumière");
            System.out.println("\t3 -> Arrêter l'application");
            int choix=in.nextInt();
            switch(choix)
            {
                case 1:
                    displayStateLight();
                    break;
                case 2:
                    forceLightOnLight();
                    break;
                case 3:
                    quitProgram=true;
                    break;
            }
        }
        while(quitProgram==false);
    }
    
}
