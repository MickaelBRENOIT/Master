/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubedecommunication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Mikael
 */
public class FrameAffichage extends JFrame {
    
    private final JLabel valeur;
    private final JButton suivant;
    private final DataInputStream in;

    public FrameAffichage(DataInputStream in) {
        super("Affichage");
        this.in = in;
// Mise en place des composants
        valeur = new JLabel("000000000000000000000", JLabel.CENTER);
        getContentPane().add(valeur, "Center");
        suivant = new JButton("Suivant");
        getContentPane().add(suivant, "South");
// Mise en places des écouteurs
        suivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    valeur.setText(String.format("%016X", in.readLong()));
                } catch (IOException ex) {
                    Logger.getLogger(FrameAffichage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
// Placement et autres fioritures
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getValeur() {
        return valeur;
    }
    
    
}
