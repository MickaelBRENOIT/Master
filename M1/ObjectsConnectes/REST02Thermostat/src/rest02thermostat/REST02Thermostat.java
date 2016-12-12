/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest02thermostat;

import java.util.Random;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 *
 * @author e1500727
 */
public class REST02Thermostat {

    /**
     * @param args the command line arguments
     */
    
    private static final String url = "http://localhost:4567/thermometre";
    private static final String charset = "UTF-8";    
    
    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
        // TODO code application logic here
        Random rand = new Random();
        while (true)
        {
            // On attend 2 secondes avant d'envoyer une nouvelle valeur
            Thread.sleep(2000);
            System.out.println("Nouveau passage : \n");
            
            //On généère une température aléatoire entre 0 et 30
            int  temperature = rand.nextInt(30);
            
            String query = String.format("Tcourante=%s", temperature);
            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            
            try (OutputStream output = connection.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream response = connection.getInputStream();
            // Obtenir le resultat de la requete
            try (Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.useDelimiter("\\A").next();
                System.out.println("\t--> "+responseBody);
            }

        }
    }
    
}
