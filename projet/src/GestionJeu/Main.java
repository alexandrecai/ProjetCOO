package GestionJeu;

import Donjon.Donjon;
import Monstre.FabriqueMonstre;
import Monstre.Monstre;
import Personnage.Hero;
import Personnage.Sorcier;
import Donjon.Piece;

public class Main {

    public static void main(String[] args) {
        ControleurJeu controleur = new ControleurJeu();
        controleur.lancementJeu();

    }
}
