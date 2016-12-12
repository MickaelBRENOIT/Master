/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest04smartphone;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.*;

/**
 *
 * @author e1500727
 */
public class REST04Smartphone {

    //Recevoir et afficher la température courante
    private static void afficherTempCourante() throws IOException 
    {
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        String url = "http://localhost:4567/smartphone";
        URLConnection connection = new URL(url).openConnection();
        connection.setDoOutput(false); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

        InputStream response = connection.getInputStream();
        try (Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.useDelimiter("\\A").next();
            System.out.println("\tReponse Body | temperature courante : "+responseBody);
            JSONObject obj = new JSONObject(responseBody);
            int temperatureCourante = obj.getInt("tempCourante");
            System.out.println("Temperature courante:" + temperatureCourante);
        }
    }
    
    //Recevoir et afficher la température courante
    private static void changerTempChauffage() throws IOException 
    {   
        Scanner in = new Scanner(System.in);
        System.out.println("Entrer la nouvelle temperature de chauffage : ");
        int temperatureChauffage=in.nextInt();
        String charset = "UTF-8";
        String url = "http://localhost:4567/bourbon";
        String query = String.format("Schauffage=%s", temperatureChauffage);
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
            System.out.println("\tReponse Body | temperature chauffage : "+responseBody);
        }
    }
    
    public static void main(String[] args) throws IOException {
        boolean quitProgram=false;
        Scanner in = new Scanner(System.in);
        do
        {
            System.out.println("Le menu:");
            System.out.println("\t1 -> Recevoir et afficher la température courante");
            System.out.println("\t2 -> Entrer au clavier et envoyer la température de chauffage au serveur");
            System.out.println("\t3 -> Arrêter l'application");
            int choix=in.nextInt();
            switch(choix)
            {
                case 1:
                    afficherTempCourante();
                    break;
                case 2:
                    changerTempChauffage();
                    break;
                case 3:
                    quitProgram=true;
                    break;
            }
        }
        while(quitProgram==false);
    }
    
}
