/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tprestintroduction;
import static spark.Spark.*;

/**
 *
 * @author Mikael
 */
public class TpRESTIntroduction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Pour traiter la requete HTTP POST http://localhost:4567/
        get("/",(request, response)->"Hello World");
        
        // Pour traiter la requete HTTP POST http://localhost:4567/hello
        get("/hello",(request, response)->{
            return "Hello Name";
        });
        
        // Pour traiter la requete HTTP GET http://localhost:4567/hello?foo=5
        post("/hello", (request, response)->{
            String val = request.queryParams("foo");
            return "Hello : "+val;
        });
    }
    
}
