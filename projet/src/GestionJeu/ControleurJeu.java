package GestionJeu;

import Donjon.Donjon;
import Donjon.Piece;
import Inventaire.Consommable;
import Inventaire.Consommables.Pain;
import Inventaire.Consommables.PotionSoin;
import Inventaire.Equipement;
import Inventaire.Equipements.Armure;
import Inventaire.Equipements.Botte;
import Inventaire.Equipements.Casque;
import Inventaire.Equipements.Epee;
import Inventaire.StrategyConsommer.Boire;
import Inventaire.StrategyConsommer.Manger;
import Monstre.Monstre;
import Personnage.FabriqueHero;
import Personnage.Hero;

import java.util.Random;
import java.util.Scanner;

public class ControleurJeu {

    Scanner scanner = new Scanner(System.in);
    Boolean continuer = true;
    Random r = new Random();

    int nombreEnnemisTues = 0;
    int nombrePiecesTermninees = 0;
    int nombreDonjonTerminees = 0;

    public void lancementJeu(){

        System.out.println("=============================================================================");
        System.out.println(" ");
        System.out.println("Veuillez choisir une classe parmis Archer, Assassin, Barbare ou Sorcier ⚔️:");
        String choix = scanner.nextLine();
        System.out.println(" ");
        System.out.println("=============================================================================");
        System.out.println(" ");
        System.out.println("Veuillez choisir un nom :");
        String nom = scanner.nextLine();
        System.out.println(" ");
        System.out.println("=============================================================================");
        System.out.println(" ");
        Hero hero = FabriqueHero.creerHero(choix,nom);
        //Ajouter affichage stats perso au debut

        while (continuer){
            deroulementDonjon(hero);
            if(continuer){
                nombreDonjonTerminees += 1;
                System.out.println("Vous etes en route pour un nouveau donjon");
                System.out.println(" ");
                System.out.println("=============================================================================");
                System.out.println(" ");
            }
        }

        afficherStatsFinales(hero);

    }

    public void deroulementDonjon(Hero hero){
        Donjon donjon = new Donjon();

        System.out.println("Vous entrez dans un donjon qui possede " + donjon.getNbPieces() + " pieces.");
        System.out.println(" ");
        for(Piece piece:donjon.getListePieces()){
            deroulementPiece(piece,hero);
            if(continuer){
                generationItemFinPiece(piece);
                nombrePiecesTermninees += 1;
                System.out.println("Rammassez l'objet ?");
                String reponse = scanner.nextLine();
                System.out.println(" ");
                System.out.println("=============================================================================");
                System.out.println(" ");
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
        }
        if(hero.getVie() > 0){
            System.out.println("Vous avez terminez le donjon");
            System.out.println(" ");
        }
    }

    public void deroulementPiece(Piece piece,Hero hero){
        if(hero.getVie() > 0){
            System.out.println("Vous entrez dans une piece ou il y a " + piece.getNbMonstres() + " ennemis.");
            System.out.println(" ");
            System.out.println("Ennemis present dans la piece :");
            for(Monstre monstre:piece.getMonstres()){
                System.out.println(monstre.getType() + " : " + monstre.getVie() + " hp" );
            }
            System.out.println(" ");
        }


        for(Monstre ennemi:piece.getMonstres()){
            while(ennemi.getVie() > 0 && hero.getVie() > 0){
                tourCombat(ennemi,hero);
            }
            if(!continuer){
                break;
            }
            for(Monstre monstre:piece.getMonstres()){
                if(monstre.getVie() > 0){
                    System.out.println(monstre.getType() + " : " + monstre.getVie() + " hp" );
                }
            }
            System.out.println(" ");
        }
    }

    public void generationItemFinPiece(Piece piece){
        int aleatoire = r.nextInt(6)+1;
        if(aleatoire == 1){
            piece.getEquipementsAuSol().add(new Casque());
        }
        if(aleatoire == 2){
            piece.getEquipementsAuSol().add(new Armure());
        }
        if(aleatoire == 3){
            piece.getEquipementsAuSol().add(new Botte());
        }
        if(aleatoire == 4){
            piece.getEquipementsAuSol().add(new Epee());
        }
        if(aleatoire == 5){
            piece.getConsommablesAuSol().add(new PotionSoin());
        }
        if(aleatoire == 6){
            piece.getConsommablesAuSol().add(new Pain());
        }
        System.out.println("Objets au sol :");
        for(Equipement e: piece.getEquipementsAuSol()){
            System.out.println(e.getType() + " " + e.getPuissance());
        }
        for(Consommable c: piece.getConsommablesAuSol()){
            System.out.println(c.getNom());
        }
        System.out.println(" ");
    }

    public void tourCombat(Monstre ennemi, Hero hero){
        System.out.println("Attaquer ou afficher inventaire :");
        String reponse = scanner.nextLine();
        System.out.println(" ");
        if(reponse.equalsIgnoreCase("Attaquer")){
            if(ennemi.getVitesse() <= hero.getVitesse()){
                System.out.println("Vous attaquez en premier");
                System.out.println(" ");
                if((ennemi.getVie() - hero.getAttaque()) > 0){
                    ennemi.setVie(ennemi.getVie() - hero.getAttaque());
                    System.out.println("Le " + ennemi.getType() + " a encore " + ennemi.getVie() + " hp.");
                    System.out.println(" ");
                    System.out.println("Le " + ennemi.getType() + " vous attaques.");
                    System.out.println(" ");
                    int degatsAPrendre = (int)(ennemi.getDegat() * (1 - hero.getDefence()));
                    if((hero.getVie() - degatsAPrendre) > 0){
                        hero.setVie(hero.getVie() - degatsAPrendre);
                        System.out.println("Il vous reste encore " + hero.getVie() + " hp.");
                        System.out.println(" ");
                    }
                    else {
                        hero.setVie(0);
                        continuer = false;
                        System.out.println("Vous etes mort.");
                        System.out.println(" ");
                    }
                }
                else{
                    ennemi.setVie(0);
                    nombreEnnemisTues += 1;
                    System.out.println("Le " + ennemi.getType() + " est mort");
                    System.out.println(" ");
                }

            }
            else {
                System.out.println("Le " + ennemi.getType() + " vous attaques en premier.");
                System.out.println(" ");
                int degatsAPrendre = (int)(ennemi.getDegat() * (1 - hero.getDefence()));
                if((hero.getVie() - (ennemi.getDegat() * ((100 - hero.getDefence())/100))) > 0){
                    hero.setVie(hero.getVie() - degatsAPrendre);
                    System.out.println("Il vous reste encore " + hero.getVie() + " hp.");
                    System.out.println(" ");
                    System.out.println("Vous attaquez le " + ennemi.getType());
                    System.out.println(" ");
                    if((ennemi.getVie() - hero.getAttaque()) > 0){
                        ennemi.setVie(ennemi.getVie() - hero.getAttaque());
                        System.out.println("Le " + ennemi.getType() + " a encore " + ennemi.getVie() + " hp.");
                        System.out.println(" ");
                    }
                    else {
                        ennemi.setVie(0);
                        nombreEnnemisTues += 1;
                        System.out.println("Le " + ennemi.getType() + " est mort");
                        System.out.println(" ");
                    }
                }
                else {
                    hero.setVie(0);
                    continuer = false;
                    System.out.println("Vous etes mort.");
                    System.out.println(" ");
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
                        System.out.println(c.getNom());
                    }
                }
                System.out.println(" ");
                affichagePropositionEquipe(hero);

            }
            else if(hero.getInventaire().getInventaireConsommable().size() > 0){
                System.out.println("Inventaire :");
                for(Consommable c:hero.getInventaire().getInventaireConsommable()){
                    System.out.println(c.getNom());
                }
                System.out.println(" ");
                affichagePropositionEquipe(hero);
            }
            else {
                System.out.println("L'inventaire est vide");
                System.out.println(" ");
            }

        }
        System.out.println("=============================================================================");
        System.out.println(" ");
    }

    public void affichagePropositionEquipe(Hero hero){
        System.out.println("Equiper un objet, consommer un element ou quitter inventaire ?");
        String reponse = scanner.nextLine();
        System.out.println(" ");
        if(reponse.equalsIgnoreCase("equiper un objet")){
            System.out.println("Saisissez le nom de l'objet a equiper :");
            String objet = scanner.nextLine();
            System.out.println(" ");
            for(Equipement e:hero.getInventaire().getInventaireEquipement()){
                String nomComplet = e.getType() + " " + e.getPuissance();
                if(nomComplet.equalsIgnoreCase(objet)){
                    e.equiper(hero);
                    break;
                }
            }
        }
        else if(reponse.equalsIgnoreCase("consommer un element")){
            System.out.println("Saisissez le nom de l'element a consommer :");
            String objet = scanner.nextLine();
            System.out.println(" ");
            for(Consommable c:hero.getInventaire().getInventaireConsommable()){
                if(objet.equalsIgnoreCase(c.getNom())){
                    if(c.getType().equals("aliment")){
                        c.consommer(new Manger(),hero);
                        hero.getInventaire().getInventaireConsommable().remove(c);
                        break;
                    }
                    else {
                        c.consommer(new Boire(),hero);
                        hero.getInventaire().getInventaireConsommable().remove(c);
                        break;
                    }
                }
            }
        }
    }

    public void afficherStatsFinales(Hero hero){
        System.out.println("Voici les stats finales de " + hero.getNom() + " : ");
        System.out.println(" ");
        System.out.println("Nombre d'ennemis tues : " + nombreEnnemisTues);
        System.out.println(" ");
        System.out.println("Nombre de pieces terminees : " + nombrePiecesTermninees);
        System.out.println(" ");
        System.out.println("Nombre de donjon terminees : " + nombreDonjonTerminees);
        System.out.println(" ");
        int score = nombreDonjonTerminees*2 + nombreEnnemisTues*4 + nombrePiecesTermninees*3;
        System.out.println("Score final : " + score);
        System.out.println(" ");
        System.out.println("=============================================================================");
        System.out.println(" ");
    }
}
