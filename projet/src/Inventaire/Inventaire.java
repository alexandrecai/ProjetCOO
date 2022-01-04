package Inventaire;

import java.util.ArrayList;

public class Inventaire {

    ArrayList<Equipement> inventaireEquipement = new ArrayList<Equipement>();
    ArrayList<Equipement> objetsEquipes = new ArrayList<Equipement>();
    ArrayList<Consommable> inventaireConsommable = new ArrayList<Consommable>();

    public ArrayList<Equipement> getInventaireEquipement() {
        return inventaireEquipement;
    }

    public void setInventaireEquipement(ArrayList<Equipement> inventaireEquipement) {
        this.inventaireEquipement = inventaireEquipement;
    }

    public ArrayList<Equipement> getObjetsEquipes() {
        return objetsEquipes;
    }

    public void setObjetsEquipes(ArrayList<Equipement> objetsEquipes) {
        this.objetsEquipes = objetsEquipes;
    }

    public ArrayList<Consommable> getInventaireConsommable() {
        return inventaireConsommable;
    }

    public void setInventaireConsommable(ArrayList<Consommable> inventaireConsommable) {
        this.inventaireConsommable = inventaireConsommable;
    }
}
