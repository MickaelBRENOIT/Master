/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam05systemedecontrole;

import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author e1500727
 */
public class Exam05SystemeDeControle {

    private static int Lumiere;
    private static boolean isLightOn;
    private static boolean isPresence;
    
    public static void main(String[] args) {
        
        get("/etatLumiere",(request, response)->{
            return "{ \"stateLight\" : "+isLightOn+"}";
        });
        
        post("/forcerAllumage", (Request request, Response response) -> {
            isLightOn = true;
            return "OK - temp courante";
        });
    }
    
}
