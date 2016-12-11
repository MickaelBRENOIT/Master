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
#include <windows.h>

using namespace std;

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
    ofstream output("SDDv2.obj");
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
    
    Sleep(5000);
    
    ofstream face;
    
    face.open("SDDv2.obj", ios::app);
    
    output << "f " << "-4 " << "-3 " << "-2 " << "-1 ";
    
    /*output << Xmin << " " << Ymin << " " << Zmin << " 0 0 0" << endl;
	output << Xmax << " " << Ymin << " " << Zmin << " 0 0 0" << endl;
	output << Xmax << " " << Ymax << " " << Zmin << " 0 0 0" << endl;
	output << Xmin << " " << Ymax << " " << Zmin << " 0 0 0" << endl;*/
	
	face.close();
    
		
}
    
    
    /*}else{
        cout << "Le fichier n'a pas pu être ouvert";
    }*/

int main(int argc, char** argv) {
    findXminXmaxYminYmax("SDDv2.txt");
    //display("SDDv2.txt");
    //convertTxtToObj("SDDv2.txt");
    //convertTxtIToObj(file);
    
}

