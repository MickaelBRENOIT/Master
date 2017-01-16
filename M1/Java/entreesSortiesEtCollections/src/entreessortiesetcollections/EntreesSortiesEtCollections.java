/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entreessortiesetcollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author e1500727
 */
public class EntreesSortiesEtCollections {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        // DEBUT 4 - PREMIERE VERSION OU IL FAUT AFFICHER LES MOTS AVEC LEUR NOMBRE D'OCCURENCES
        //Ouvre le fichier
        /*Scanner in = new Scanner(new File("ilsCassentLeMonde.txt"));
        
        //Stock les mots dans l'arrayList
        List<String> listOfWords = new ArrayList<String>();
        while(in.hasNext()){
            listOfWords.add(in.next());
        }
        in.close();
        
        //clés (les mots) - valeurs (le nombre de fois qu'ils apparaissent) 
        Set<String> occurences = new HashSet<String>(listOfWords);
        for(String key : occurences){
            System.out.println(key + ": " + Collections.frequency(listOfWords, key));
        }*/
        // FIN 4 - PREMIERE VERSION OU IL FAUT AFFICHER LES MOTS AVEC LEUR NOMBRE D'OCCURENCES
    
        // ----------------------------------------------------------------------------------------
        
        // DEBUT 2 - SECONDE VERSION OU IL FAUT AFFICHER PAR ORDRE ALPHABETIQUE
        /*Scanner in = new Scanner(new File("ilsCassentLeMonde.txt"));
        HashSet test = new HashSet();
        while(in.hasNext()){
            test.add(in.next());
        }
        in.close();
        
        TreeSet myTreeSet = new TreeSet();
        myTreeSet.addAll(test);
        System.out.println(myTreeSet);*/
        // FIN 2 - SECONDE VERSION OU IL FAUT AFFICHER PAR ORDRE ALPHABETIQUE
        
        // DEBUT 3 - ORDRE D'INSERTION
        /*Scanner in = new Scanner(new File("ilsCassentLeMonde.txt"));
        while(in.hasNext()){
            System.out.println(in.next());
        }
        in.close();*/
        // FIN 3 - ORDRE D'INSERTION
        
        // DEBUT 1 - MOTS DISTINCS
        Scanner in = new Scanner(new File("ilsCassentLeMonde.txt"));
        in.useDelimiter("[\\s ;,. ? !’' :\\/\"()]+") ;
        
        //Set<String> unique = new HashSet<String>();           //exo 1
        Set<String> unique = new TreeSet<String>();             //exo 2
        //Set<String> unique = new LinkedHashSet<String>();     //exo 3
        while(in.hasNext()){
            unique.add(in.next().toLowerCase());
        }
        
        System.out.println(unique.size());
        System.out.println(unique);
        // FIN 1 - MOTS DISTINCS
        
    }
    
}
