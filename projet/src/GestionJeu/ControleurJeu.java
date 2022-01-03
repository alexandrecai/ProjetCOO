package GestionJeu;


import Personnage.FabriqueHero;
import Personnage.Hero;

import java.util.Scanner;

public class ControleurJeu {

    Scanner scanner = new Scanner(System.in);

    public void lancementJeu(){

        System.out.println("Veuillez choisir une classe parmi Archer, Assassin, Barbare ou Sorcier :");
        String choix = scanner.nextLine();
        System.out.println("Veuillez choisir un nom :");
        String nom = scanner.nextLine();
        Hero hero = FabriqueHero.creerHero(choix,nom);

    }

    public void deroulementDonjon(){

    }
}
