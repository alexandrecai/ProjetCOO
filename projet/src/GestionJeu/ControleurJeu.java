package GestionJeu;

import Donjon.Donjon;
import Donjon.Piece;
import Monstre.Monstre;
import Personnage.FabriqueHero;
import Personnage.Hero;

import java.util.Scanner;

public class ControleurJeu {

    Scanner scanner = new Scanner(System.in);

    public void lancementJeu(){

        System.out.println("Veuillez choisir une classe parmi Archer, Assassin, Barbare ou Sorcier ⚔️:");
        String choix = scanner.nextLine();
        System.out.println("Veuillez choisir un nom :");
        String nom = scanner.nextLine();
        Hero hero = FabriqueHero.creerHero(choix,nom);

        deroulementDonjon(hero);

    }

    public void deroulementDonjon(Hero hero){
        Donjon donjon = new Donjon();

        System.out.println("Vous entrez dans un donjon qui possede " + donjon.getNbPieces() + " pieces.");
        for(Piece piece:donjon.getListePieces()){
            deroulementPiece(piece,hero);
        }
        if(hero.getVie() > 0){
            System.out.println("Vous avez terminez le donjon");
        }
    }

    public void deroulementPiece(Piece piece,Hero hero){
        if(hero.getVie() > 0){
            System.out.println("Vous entrez dans une piece ou il y a " + piece.getNbMonstres() + " ennemis.");
            System.out.println("Ennemis present dans la piece :");
            for(Monstre monstre:piece.getMonstres()){
                System.out.println(monstre.getType() + " : " + monstre.getVie() + " hp" );
            }
        }


        for(Monstre ennemi:piece.getMonstres()){
            while(ennemi.getVie() > 0 && hero.getVie() > 0){
                tourCombat(ennemi,hero);
            }
        }
    }

    public void tourCombat(Monstre ennemi, Hero hero){
        System.out.println("Attaquer ou utiliser un objet :");
        String reponse = scanner.nextLine();
        if(reponse.equals("Attaquer")){
            if(ennemi.getVitesse() <= hero.getVitesse()){
                System.out.println("Vous attaquez en premier");
                if((ennemi.getVie() - hero.getAttaque()) > 0){
                    ennemi.setVie(ennemi.getVie() - hero.getAttaque());
                    System.out.println("Le " + ennemi.getType() + " a encore " + ennemi.getVie() + " hp.");
                    System.out.println("Le " + ennemi.getType() + " vous attaques.");
                    int degatsAPrendre = (int)(ennemi.getDegat() * (1 - hero.getDefence()));
                    if((hero.getVie() - degatsAPrendre) > 0){
                        hero.setVie(hero.getVie() - degatsAPrendre);
                        System.out.println("Il vous reste encore " + hero.getVie() + " hp.");
                    }
                    else {
                        hero.setVie(0);
                        System.out.println("Vous etes mort.");
                    }
                }
                else{
                    ennemi.setVie(0);
                    System.out.println("Le " + ennemi.getType() + " est mort");
                }

            }
            else {
                System.out.println("Le " + ennemi.getType() + " vous attaques en premier.");
                int degatsAPrendre = (int)(ennemi.getDegat() * (1 - hero.getDefence()));
                if((hero.getVie() - (ennemi.getDegat() * ((100 - hero.getDefence())/100))) > 0){
                    hero.setVie(hero.getVie() - degatsAPrendre);
                    System.out.println("Il vous reste encore " + hero.getVie() + " hp.");
                    System.out.println("Vous attaquez le " + ennemi.getType());
                    if((ennemi.getVie() - hero.getAttaque()) > 0){
                        ennemi.setVie(ennemi.getVie() - hero.getAttaque());
                        System.out.println("Le " + ennemi.getType() + " a encore " + ennemi.getVie() + " hp.");
                    }
                    else {
                        ennemi.setVie(0);
                        System.out.println("Le " + ennemi.getType() + " est mort");
                    }
                }
                else {
                    hero.setVie(0);
                    System.out.println("Vous etes mort.");
                }
            }
        }
    }
}
