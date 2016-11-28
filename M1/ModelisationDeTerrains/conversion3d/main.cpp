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

int main(int argc, char** argv) {
    ifstream file("test.txt");
    //display(file);
    convertTxtIToObj(file);
    
}

