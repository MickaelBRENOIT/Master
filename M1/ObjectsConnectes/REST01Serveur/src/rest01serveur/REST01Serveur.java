/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest01serveur;
import spark.Spark.*;
import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.post;

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
 * @author Mikael
 */
public class REST01Serveur {

    /**
     * @param args the command line arguments
     */
    
    //variable qui contient la température
    private static int tempCourante = 15;
    //variable qui contient à la température à partir de laquelle la pièce doit commencer à être chauffée.
    private static int tempChauffage = 15;
    //variable qui dicte l'état de fonctionne du radiateur
    private static boolean etatDeFonctionnement = false;
    
    public static void main(String[] args) {
        
        //Requête venant du Smarphone pour connaître la température courante.
        //Le serveur retourne la température courante au format JSON
        get("/smartphone",(request, response)->{
                return "{ \"tempCourante\" : "+tempCourante+"}";
        });
    
        //Requête venant du radiateur pour connaître sont état de fonctionnement.
        //Le serveur retourne l'état de fonctionnement du radiateur au format JSON.
        get("/radiateur",(request, response)->{
            if(etatDeFonctionnement)
                return "{ \"etatDeFonctionnement\" : \"true\"}";
            else
                return "{ \"etatDeFonctionnement\" : \"false\"}";
        });
    
        //Requête pour indiquer la température courante. Le serveur doit changer la
        //variable correspondant à l'état de fonctionnement du radiateur en fonction
        //de cette nouvelle température courante et de la température de chauffage.
        post("/thermometre", (Request request, Response response) -> {
            String val=request.queryParams("Tcourante");
            tempCourante=Integer.parseInt(val);
            if(tempCourante < tempChauffage)
                etatDeFonctionnement = true;
            else
                etatDeFonctionnement = false;
            System.out.println("Nouvelle temperature courante: "+tempCourante);
            return "OK - temp courante";
        });
        
        //Requête venant du smarphone pour changer la température de chauffage. 
        //Le serveur doit changer la variable correspondant à l’état de fonctionnement
        //du radiateur en fonction de le température courante et decette nouvelle
        //température de chauffage.
        post("/bourbon", (Request request, Response response) -> {
            String val=request.queryParams("Schauffage");
            tempChauffage=Integer.parseInt(val);
            if(tempCourante < tempChauffage)
                etatDeFonctionnement = true;
            else
                etatDeFonctionnement = false;
            System.out.println("Nouvelle temperature de chauffage: "+tempChauffage);
            return "OK - temp chauffage";
        });
    }
}
