package Personnage;

import Monstre.Monstre;

public class FabriqueHero {

    public static Hero creerHero(String type, String nom){
        if("Archer".equalsIgnoreCase(type)) return new Archer(nom);
        else if ("Assassin".equalsIgnoreCase(type)) return new Assassin(nom);
        else if ("Barbare".equalsIgnoreCase(type)) return new Barbare(nom);
        else if ("Sorcier".equalsIgnoreCase(type)) return new Sorcier(nom);

        return null;
    }
}
