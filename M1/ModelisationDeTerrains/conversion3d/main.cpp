/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: Mikael
 *
 * Created on 25 novembre 2016, 10:28
 */
#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>
#include <climits>

using namespace std;

void display(ifstream &file){
    string str; 
    while (getline(file, str, ' '))
    {
        cout << str << "\t";
    }
}

void convertTxtToObj(ifstream &input){
    ofstream output("terrain.obj");
    string str;
    if(output.is_open()){
        while(getline(input, str)){
            output << "v " << str << "\n";
        }
    }else{
        cout << "Le fichier n'a pas pu être ouvert";
    }
}

void convertTxtIToObj(ifstream &file){
    ofstream output("terrainI.obj");
    string str; 
    int cpt=0;
    if(output.is_open()){
        while(getline(file, str))
        {
            output << "v ";
            for(int i=0; i<str.length(); i++){
                if(cpt<4){
                    if (isspace(str.at(i)))
                        ++cpt;
                    output << str.at(i);
                }
            }
            cpt=0;
            output << "\n";
        }
    }else{
        cout << "Le fichier n'a pas pu être ouvert";
    }
}

void findXminXmaxYminYmax(ifstream &input){
    //ofstream output("terrainAvecCarre.obj");
    string str;
    int cpt=0;
    double Xmin=LONG_MAX, Ymin=LONG_MAX, Xmax=LONG_MIN, Ymax=LONG_MIN, Zmin=LONG_MAX;
    //if(output.is_open()){
        while(getline(input, str, ' '))
        {
                      
            cpt = cpt + 1;
            cout << cpt << " + " << str << '\n'; 
            
            //colonne des X
            double temp = atof(str.c_str());
            //sscanf_s(str.c_str, " caractère l'espace ", &a, &b...)
            if(cpt==1){
                //cout << "x:" << str << "\n";
                if(temp<Xmin)
                    Xmin = temp;
                if(temp>Xmax)
                    Xmax = temp;
            }else if(cpt==2){
                //cout << "y:" << str << "\n";
                if(temp<Ymin)
                    Ymin = temp;
                if(temp>Ymax)
                    Ymax = temp;
            }else if(cpt==3){
                //cout << "z:" << str << "\n";
                if(temp<Zmin)
                    Zmin = temp;
            }else if (cpt>=6){
                cpt=0;
                str.clear();
            }       
        }
    
    cout << "XMin=" << Xmin << "\n";
    cout << "XMax=" << Xmax << "\n";
    
    cout << "YMin=" << Ymin << "\n";
    cout << "YMax=" << Ymax << "\n";
    
    cout << "ZMin=" << Zmin << "\n";
    
    
    /*}else{
        cout << "Le fichier n'a pas pu être ouvert";
    }*/
}

int main(int argc, char** argv) {
    ifstream file("test.txt");
    findXminXmaxYminYmax(file);
    //display(file);
    
}

