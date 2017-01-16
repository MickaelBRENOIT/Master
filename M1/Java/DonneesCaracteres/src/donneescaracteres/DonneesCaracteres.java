/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donneescaracteres;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Mikael
 */
public class DonneesCaracteres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*try(Scanner in = new Scanner(new FileInputStream("doubles")); DataOutputStream out = new DataOutputStream(new FileOutputStream("data"))){
            while(in.hasNextDouble()){
                out.writeDouble(in.nextDouble());
            }
        }*/
        
        try(DataInputStream in = new DataInputStream(new FileInputStream("data")); FileWriter out = new FileWriter(new File("double2"))){
            double d;
            while (in.available() > 0){
                out.write(Double.toString(in.readDouble()) + "\n");
            }
        }
        
    }
    
}
