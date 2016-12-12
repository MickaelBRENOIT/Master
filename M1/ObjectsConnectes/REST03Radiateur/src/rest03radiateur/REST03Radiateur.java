/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest03radiateur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.*;

/**
 *
 * @author e1500727
 */
public class REST03Radiateur {

    /**
     * @param args the command line arguments
     */
    
    private static final String url = "http://localhost:4567/radiateur";
    private static final String charset = "UTF-8"; 
    
    public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {
        while (true)
        {
            Thread.sleep(2000);
            System.out.println("Nouveau passage : ");
            
            //connexion à l'url
            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(false);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            
            InputStream response = connection.getInputStream();
            // Obtenir le resultat de la requete
            try (Scanner scanner = new Scanner(response)) 
            {
                String responseBody = scanner.useDelimiter("\\A").next();
                System.out.println("\t==> "+responseBody);
                
                JSONObject obj = new JSONObject(responseBody);
                String radiateurMarche=obj.getString("etatDeFonctionnement");
                if (radiateurMarche.contains("true"))
                     System.out.println("Radiateur est allumé");
                else
                     System.out.println("Radiateur est éteint");
            }

        }
    }
    
}
