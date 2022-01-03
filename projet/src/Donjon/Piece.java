package Donjon;

import Monstre.Monstre;
import Monstre.FabriqueMonstre;

import java.util.ArrayList;
import java.util.Random;

public class Piece {

    ArrayList<Monstre> monstres = new ArrayList<Monstre>();
    Random r = new Random();
    int nbMonstres = r.nextInt(5) + 1;
    FabriqueMonstre fabriqueMonstre = new FabriqueMonstre();


    public Piece() {
        for (int i = 0; i < nbMonstres; i++) {
            monstres.add(fabriqueMonstre.creerMonstre(r.nextInt(3)));
        }
    }

    public Monstre getMonstre(int indice){
        return monstres.get(indice);
    }

    public int getNbMonstres() {
        return monstres.size();
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }
}
