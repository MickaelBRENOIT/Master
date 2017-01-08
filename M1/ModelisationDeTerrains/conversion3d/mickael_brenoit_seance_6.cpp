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
#include <vector>

using namespace std;

//D�but - programmes des s�ances 2 et 3

void display(char* f) {
    ifstream file(f);
    string str;
    while (getline(file, str, ' ')) {
        cout << str << "\t";
    }
}

void convertTxtToObj(char* in) {
    ifstream input(in);
    ofstream output("seance6.obj");
    string str;
    if (output.is_open()) {
        while (getline(input, str)) {
            output << "v " << str << "\n";
        }
    } else {
        cout << "Le fichier n'a pas pu être ouvert";
    }
}

void convertTxtIToObj(char* f) {
    ifstream file(f);
    ofstream output("terrainI.obj");
    string str;
    int cpt = 0;
    if (output.is_open()) {
        while (getline(file, str)) {
            output << "v ";
            for (int i = 0; i < str.length(); i++) {
                if (cpt < 4) {
                    if (isspace(str.at(i)))
                        ++cpt;
                    output << str.at(i);
                }
            }
            cpt = 0;
            output << "\n";
        }
    } else {
        cout << "Le fichier n'a pas pu être ouvert";
    }
}
//Fin - Programme des s�ances 2 et 3

//Programme de la s�ance 4

void findXminXmaxYminYmax(char* in) {
    ifstream input(in);
    ofstream output;
    string str;
    int cpt = 0;
    const double vn = 0.2, vt = 0.5;
    double Xmin = LONG_MAX, Ymin = LONG_MAX, Xmax = LONG_MIN, Ymax = LONG_MIN, Zmin = LONG_MAX;
    double x, y, z, r, g, b;
    if (input.is_open()) {
        while (!input.eof()) {
            input >> x >> y >> z >> r >> g >> b;
            if (x < Xmin)
                Xmin = x;
            if (x > Xmax)
                Xmax = x;
            if (y < Ymin)
                Ymin = y;
            if (y > Ymax)
                Ymax = y;
            if (z < Zmin)
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

//Sujet s�ance 5 -Le programme pour parcourir le damier avec des quadrilat�res ayant des altitudes augmentantes (g�n�rant le fichier OBJ des quadrilat�res incluant le nuage de points)

void altitudesAugmentantes(char* in) {
    ifstream input(in);
    double Xmin = LONG_MAX, Ymin = LONG_MAX, Xmax = LONG_MIN, Ymax = LONG_MIN, Zmin = LONG_MAX, tempX, tempY, tempZ, translation, profondeur;
    double x, y, z, r, g, b;
    if (input.is_open()) {
        while (!input.eof()) {
            input >> x >> y >> z >> r >> g >> b;
            if (x < Xmin)
                Xmin = x;
            if (x > Xmax)
                Xmax = x;
            if (y < Ymin)
                Ymin = y;
            if (y > Ymax)
                Ymax = y;
            if (z < Zmin)
                Zmin = z;
        }
    }

    input.close();

    convertTxtToObj(in);

    tempX = Xmin;
    tempY = Ymin;
    tempZ = Zmin;
    translation = fabs(fabs(Xmax) - fabs(Xmin)) / 10;
    profondeur = translation / 50;

    ofstream face;
    face.open("seance5.obj", ios::app);

    while (tempY < Ymax) {
        while (tempX < Xmax) {
            face << "v " << tempX << " " << tempY << " " << tempZ << endl;
            face << "v " << tempX + translation << " " << tempY << " " << tempZ << endl;
            face << "v " << tempX + translation << " " << tempY + translation << " " << tempZ << endl;
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

//Sujet s�ance 5 - Le programme superposant au nuage les quadrilatères avec des altitudes moyennes (g�n�rant le fichier OBJ des quadrilat�res incluant le nuage de points)
//	A.	prendre la moyenne des hauteurs qui se trouvent dans chaque quadrilat�re
//	B.	g�n�rer le nouveau quadrilat�re des hauteurs moyennes
//	C.	enlever les points qui ont �t� utilis�s dans le jeu de donn�es

//Stock les donn�es de chaque point pour les enlever une fois qu'ils sont utilis�s

struct Ligne {
    double xL;
    double yL;
    double zL;
    double rL;
    double gL;
    double bL;
};

void altitudesMoyennes(char* in) {
    int compteur = 0;
    vector<Ligne> sommet;
    ifstream input(in);
    double Xmin = LONG_MAX, Ymin = LONG_MAX, Xmax = LONG_MIN, Ymax = LONG_MIN, Zmin = LONG_MAX, tempX, tempY, translation;
    double x, y, z, r, g, b;

    if (input.is_open()) {
        while (!input.eof()) {
            input >> x >> y >> z >> r >> g >> b;
            if (x < Xmin)
                Xmin = x;
            if (x > Xmax)
                Xmax = x;
            if (y < Ymin)
                Ymin = y;
            if (y > Ymax)
                Ymax = y;
            if (z < Zmin)
                Zmin = z;

            //on stock le nuage de points ligne par ligne
            //on pourra ainsi les enlever lorsque les quadrilat�res seront trait�s	
            sommet.push_back(Ligne());
            sommet[compteur].xL = x;
            sommet[compteur].yL = y;
            sommet[compteur].zL = z;
            sommet[compteur].rL = r;
            sommet[compteur].gL = g;
            sommet[compteur].bL = b;

            compteur++;
        }
    }

    input.close();
    convertTxtToObj(in);

    tempX = Xmin;
    tempY = Ymin;
    translation = fabs(fabs(Xmax) - fabs(Xmin)) / 5;
    double ZMoyen = 0.0;
    double ZCumule = 0.0;
    int nombreDePointsDansLaRegion = 0;

    ofstream face;
    face.open("seance6.obj", ios::app);

    while (tempY < Ymax) {
        while (tempX < Xmax) {
            //chercher les points (ainsi que leur nombre) qui sont dans la m�me r�gion
            for (int i = 0; i < sommet.size(); i++) {
                //cout << i << endl;
                if (tempX < sommet.at(i).xL && tempX + translation > sommet.at(i).xL &&
                        tempY < sommet.at(i).yL && tempY + translation > sommet.at(i).yL) {
                    nombreDePointsDansLaRegion++;
                    ZCumule += sommet.at(i).zL;
                }
            }

            //si la r�gion existe alors on cr�e le quadrilat�re moyen
            if (nombreDePointsDansLaRegion != 0) {
                
                ZMoyen = ZCumule / nombreDePointsDansLaRegion;

                face << "v " << tempX << " " << tempY << " " << ZMoyen << endl;
                face << "v " << tempX + translation << " " << tempY << " " << ZMoyen << endl;
                face << "v " << tempX + translation << " " << tempY + translation << " " << ZMoyen << endl;
                face << "v " << tempX << " " << tempY + translation << " " << ZMoyen << endl;

                //face << "f -4 -3 -2 -1" << endl;
                face << "f -1 -2 -3" << endl;
                face << "f -4 -1 -3" << endl;

            }

            //on retire les points utilis�s dans le jeu de donn�es
            for (int k = 0; k < sommet.size(); k++) {
                if (tempX < sommet.at(k).xL && tempX + translation > sommet.at(k).xL &&
                        tempY < sommet.at(k).yL && tempY + translation > sommet.at(k).yL) {
                    sommet.erase(sommet.begin() + k);
                }
            }

            tempX += translation;
            ZMoyen = 0.0;
            ZCumule = 0.0;
            nombreDePointsDansLaRegion = 0;
        }
        tempX = Xmin;
        tempY += translation;
    }

    face.close();


}
//Fin - altitudes moyennes

int main(int argc, char** argv) {
    altitudesMoyennes("seance6.txt");
    return 0;
}

