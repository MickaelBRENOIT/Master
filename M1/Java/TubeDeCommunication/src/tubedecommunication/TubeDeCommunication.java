/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubedecommunication;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import tubedecommunication.FrameAffichage;
/**
 *
 * @author Mikael
 */
public class TubeDeCommunication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // PREMIERE PARTIE
        DataInputStream in = new DataInputStream(new FileInputStream("doubles"));
        FrameAffichage fa = new FrameAffichage(in);
        
        //SECONDE PARTIE
        DataOutputStream out = new DataOutputStream(new FileOutputStream("data"));
        FrameSaisie fs = new FrameSaisie(out);
        
        //TROISIEME PARTIE - http://docs.oracle.com/javase/7/docs/api/java/io/PipedInputStream.html 
        PipedOutputStream pout = new PipedOutputStream();
        PipedInputStream pin = new PipedInputStream();
        
        try{
            pin.connect(pout);
            pout.write(fs.getValue().getBytes());
            System.out.println(pin.read());
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
}
