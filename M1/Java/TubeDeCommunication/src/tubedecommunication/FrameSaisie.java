/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubedecommunication;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Mikael
 */
public class FrameSaisie extends JFrame{
    private final JLabel label;
    private final JTextField input;
    private final DataOutputStream out;
    private String value = "1.2";
    private double doubleValue;
    public FrameSaisie(DataOutputStream out) {
        super("Affichage");
        this.out = out;
// Mise en place des composants
        label = new JLabel("Entrez un double", JLabel.CENTER);
        getContentPane().add(label, "Center");
        input = new JTextField();
        getContentPane().add(input, "South");
// Gestion de l'évènement click
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                    value = input.getText();
                    doubleValue = Double.parseDouble(value);
                    try {
                        out.writeDouble(doubleValue);
                        input.setText("Value save in file");
                    } catch (IOException ex) {
                        Logger.getLogger(FrameSaisie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                      
        });
// Placement et autres fioritures
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getValue() {
        return value;
    }
    
}
