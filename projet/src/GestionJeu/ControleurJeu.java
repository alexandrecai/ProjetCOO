package GestionJeu;

import Donjon.Donjon;
import Donjon.Piece;
import Inventaire.Consommable;
import Inventaire.Equipement;
import Inventaire.Equipements.Armure;
import Inventaire.Equipements.Casque;
import Monstre.Monstre;
import Personnage.FabriqueHero;
import Personnage.Hero;

import java.util.Random;
import java.util.Scanner;

public class ControleurJeu {

    Scanner scanner = new Scanner(System.in);
    Boolean continuer = true;
    Random r = new Random();

    public void lancementJeu(){

        System.out.println("Veuillez choisir une classe parmi Archer, Assassin, Barbare ou Sorcier ⚔️:");
        String choix = scanner.nextLine();
        System.out.println("Veuillez choisir un nom :");
        String nom = scanner.nextLine();
        Hero hero = FabriqueHero.creerHero(choix,nom);

        while (continuer){
            deroulementDonjon(hero);
            System.out.println("Vous etes en route pour un nouveau donjon");
        }


    }

    public void deroulementDonjon(Hero hero){
        Donjon donjon = new Donjon();

        System.out.println("Vous entrez dans un donjon qui possede " + donjon.getNbPieces() + " pieces.");
        for(Piece piece:donjon.getListePieces()){
            deroulementPiece(piece,hero);
            generationItemFinPiece(piece);
            System.out.println("Rammassez l'objet ?");
            String reponse = scanner.nextLine();
            if(reponse.equals("oui")){
                if(piece.getConsommablesAuSol().size() >0){
                    for(Consommable c: piece.getConsommablesAuSol()){
                        hero.getInventaire().getInventaireConsommable().add(c);
                    }

                }
                else {
                    for(Equipement e: piece.getEquipementsAuSol()){
                        hero.getInventaire().getInventaireEquipement().add(e);
                    }
                }
            }
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
            for(Monstre monstre:piece.getMonstres()){
                if(monstre.getVie() > 0){
                    System.out.println(monstre.getType() + " : " + monstre.getVie() + " hp" );
                }
            }
        }
    }

    public void generationItemFinPiece(Piece piece){
        int aleatoire = r.nextInt(2)+1;
        if(aleatoire == 1){
            piece.getEquipementsAuSol().add(new Casque());
        }
        if(aleatoire == 2){
            piece.getEquipementsAuSol().add(new Armure());
        }
        System.out.println("Objets au sol :");
        for(Equipement e: piece.getEquipementsAuSol()){
            System.out.println(e.getType() + " " + e.getPuissance());
        }
    }

    public void tourCombat(Monstre ennemi, Hero hero){
        System.out.println("Attaquer ou afficher inventaire :");
        String reponse = scanner.nextLine();
        if(reponse.equalsIgnoreCase("Attaquer")){
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
                        continuer = false;
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
                    continuer = false;
                    System.out.println("Vous etes mort.");
                }
            }
        }
        if(reponse.equalsIgnoreCase("afficher inventaire")){
            if(hero.getInventaire().getInventaireEquipement().size() > 0 ){
                System.out.println("Inventaire :");
                for(Equipement e:hero.getInventaire().getInventaireEquipement()){
                    System.out.println(e.getType() + " " + e.getPuissance());
                }
                if(hero.getInventaire().getInventaireConsommable().size() > 0){
                    for(Consommable c:hero.getInventaire().getInventaireConsommable()){
                        System.out.println("Ajouter affichage consommable");
                    }
                }
                affichagePropositionEquipe(hero);

            }
            else if(hero.getInventaire().getInventaireConsommable().size() > 0){
                System.out.println("Inventaire :");
                for(Consommable c:hero.getInventaire().getInventaireConsommable()){
                    System.out.println("Ajouter affichage consommable");
                }
            }
            else {
                System.out.println("L'inventaire est vide");
            }
        }
    }

    public void affichagePropositionEquipe(Hero hero){
        System.out.println("Equiper un objet ou quitter inventaire ?");
        String reponse = scanner.nextLine();
        if(reponse.equalsIgnoreCase("equiper un objet")){
            System.out.println("Saisissez le nom de l'objet a equiper :");
            String objet = scanner.nextLine();
            for(Equipement e:hero.getInventaire().getInventaireEquipement()){
                String nomComplet = e.getType() + " " + e.getPuissance();
                if(nomComplet.equalsIgnoreCase(objet)){
                    e.equiper(hero);
                    break;
                }
            }
        }
    }
}
