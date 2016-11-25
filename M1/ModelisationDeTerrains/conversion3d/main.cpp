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

int main(int argc, char** argv) {
    ifstream file("terrain.txt");
    string str; 
    while (getline(file, str, ','))
    {
        cout << str << "\t";
    }
}

