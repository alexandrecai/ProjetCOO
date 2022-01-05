package Inventaire;

import Inventaire.StrategyConsommer.ConsommerStrategy;
import Personnage.Hero;

public class Consommable {

    int soin;
    String type;
    String nom;

    public Consommable(int soin, String type, String nom) {
        this.soin = soin;
        this.type = type;
        this.nom = nom;
    }

    public int getSoin() {
        return soin;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public void consommer(ConsommerStrategy methode, Hero hero){
        int soin = this.getSoin();
        methode.consommer(soin, hero);
    }

}
