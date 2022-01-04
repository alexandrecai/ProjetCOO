package Donjon;

import Inventaire.Consommable;
import Inventaire.Equipement;
import Monstre.Monstre;
import Monstre.FabriqueMonstre;

import java.util.ArrayList;
import java.util.Random;

public class Piece {

    ArrayList<Monstre> monstres = new ArrayList<Monstre>();
    Random r = new Random();
    int nbMonstres = r.nextInt(5) + 1;
    FabriqueMonstre fabriqueMonstre = new FabriqueMonstre();
    ArrayList<Equipement> equipementsAuSol = new ArrayList<Equipement>();
    ArrayList<Consommable> consommablesAuSol = new ArrayList<Consommable>();


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

    public ArrayList<Equipement> getEquipementsAuSol() {
        return equipementsAuSol;
    }

    public void setEquipementsAuSol(ArrayList<Equipement> equipementsAuSol) {
        this.equipementsAuSol = equipementsAuSol;
    }

    public ArrayList<Consommable> getConsommablesAuSol() {
        return consommablesAuSol;
    }

    public void setConsommablesAuSol(ArrayList<Consommable> consommablesAuSol) {
        this.consommablesAuSol = consommablesAuSol;
    }
}
