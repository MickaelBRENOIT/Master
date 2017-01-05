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
#include <math.h>
#include <cstdlib>
#include <iostream>
#include <fstream>
#include <string>
#include <climits>
#include <windows.h>

using namespace std;

//Début - programmes des séances 2 et 3
void display(char* f){
	ifstream file(f);
    string str; 
    while (getline(file, str, ' '))
    {
        cout << str << "\t";
    }
}

void convertTxtToObj(char* in){
	ifstream input(in);
    ofstream output("seance5.obj");
    string str;
    if(output.is_open()){
        while(getline(input, str)){
            output << "v " << str << "\n";
        }
    }else{
        cout << "Le fichier n'a pas pu être ouvert";
    }
}

void convertTxtIToObj(char* f){
	ifstream file(f);
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
//Fin - Programme des séances 2 et 3

//Programme de la séance 4
void findXminXmaxYminYmax(char* in){
    ifstream input(in);
    ofstream output;
    string str;
    int cpt=0;
    const double vn = 0.2, vt = 0.5;
    double Xmin=LONG_MAX, Ymin=LONG_MAX, Xmax=LONG_MIN, Ymax=LONG_MIN, Zmin=LONG_MAX;
    double x,y,z,r,g,b;
    if(input.is_open()){
    	while(!input.eof()){
    		input >> x >> y >> z >> r >> g >> b;
    		if(x<Xmin)
    			Xmin = x;
    		if(x>Xmax)
    			Xmax = x;
    		if(y<Ymin)
    			Ymin = y;
    		if(y>Ymax)
    			Ymax = y;
    		if(z<Zmin)
    			Zmin = z;
		}
	}
	
    input.close();

    output.open(in, ios::app);
    output << endl;
    output << Xmin << " " << Ymin << " " << Zmin << " 0 0 0" << endl;
    output << Xmax << " " << Ymin << " " << Zmin << " 0 0 0" << endl;
    output << Xmax << " " << Ymax << " " << Zmin << " 0 0 0" << endl;
    output << Xmin << " " << Ymax << " " << Zmin << " 0 0 0" << endl;

    output.close();

    convertTxtToObj(in);

    cout << "XMin=" << Xmin << "\n";
    cout << "XMax=" << Xmax << "\n";
    cout << "YMin=" << Ymin << "\n";
    cout << "YMax=" << Ymax << "\n";
    cout << "ZMin=" << Zmin << "\n";

    ofstream face;
    face.open("SDDv2.obj", ios::app);

    face << "f " << "-4 " << "-3 " << "-2 " << "-1 ";

    face.close();
    		
}

//Sujet séance 5 -Le programme pour parcourir le damier avec des quadrilatères ayant des altitudes augmentantes (générant le fichier OBJ des quadrilatères incluant le nuage de points)
void altitudesAugmentantes(char* in){
    ifstream input(in);
    double Xmin=LONG_MAX, Ymin=LONG_MAX, Xmax=LONG_MIN, Ymax=LONG_MIN, Zmin=LONG_MAX, tempX, tempY, tempZ, translation, profondeur;
    double x,y,z,r,g,b;
    if(input.is_open()){
    	while(!input.eof()){
    		input >> x >> y >> z >> r >> g >> b;
    		if(x<Xmin)
    			Xmin = x;
    		if(x>Xmax)
    			Xmax = x;
    		if(y<Ymin)
    			Ymin = y;
    		if(y>Ymax)
    			Ymax = y;
    		if(z<Zmin)
    			Zmin = z;
		}
	}
	
    input.close();
    
    convertTxtToObj(in);
    
    tempX = Xmin; tempY = Ymin; tempZ = Zmin;
    translation = fabs(fabs(Xmax)- fabs(Xmin)) / 10;
    profondeur = translation / 20;

    ofstream face;
    face.open("seance5.obj", ios::app);
    
    while(tempY < Ymax)
    {
        while(tempX < Xmax)
        {
            face << "v " << tempX << " " << tempY << " " << tempZ << endl;
            face << "v " << tempX + translation << " " << tempY << " " << tempZ << endl;
            face << "v " << tempX + translation << " " << tempY + translation<< " " << tempZ << endl;
            face << "v " << tempX << " " << tempY + translation << " " << tempZ << endl;
            
            face << "f -4 -3 -2 -1" << endl;
            
            tempZ += profondeur;
            tempX += translation;
        }
        tempX = Xmin;
        tempY += translation;
    }
    
    face.close();
    		
}

//Sujet séance 5 - Le programme superposant au nuage les quadrilatères avec des altitudes moyennes (générant le fichier OBJ des quadrilatères incluant le nuage de points)


int main(int argc, char** argv) {
    altitudesAugmentantes("seance5.txt");
}

