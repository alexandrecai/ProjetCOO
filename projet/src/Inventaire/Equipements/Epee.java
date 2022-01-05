package Inventaire.Equipements;

import Inventaire.Equipement;
import Personnage.Hero;

import java.util.Random;

public class Epee implements Equipement {

    int attaque;
    double defence;
    String type;
    String puissance;
    Random r = new Random();

    public Epee() {
        this.type = "epee";
        int rarete = r.nextInt(3) + 1;

        if(rarete == 1){
            this.attaque = 10;
            this.puissance = rarete + " etoile";
        }
        else if(rarete == 2){
            this.attaque = 15;
            this.puissance = rarete + " etoiles";
        }
        else {
            this.attaque = 20;
            this.puissance = rarete + " etoiles";
        }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getPuissance() {
        return this.puissance;
    }

    @Override
    public double getDefence() {
        return this.defence;
    }

    @Override
    public int getAttaque() {
        return this.attaque;
    }

    @Override
    public void equiper(Hero hero) {
        Boolean pasEquiper = true;
        for (Equipement e: hero.getInventaire().getObjetsEquipes()){
            if(e.getType().equals(this.getType())){
                hero.getInventaire().getObjetsEquipes().remove(e);
                hero.getInventaire().getObjetsEquipes().add(this);
                hero.setAttaque(hero.getAttaque() - e.getAttaque());
                hero.setAttaque(hero.getAttaque() + this.getAttaque());
                pasEquiper = false;
                System.out.println("Vous venez de remplacer votre epee");
                System.out.println(" ");
                System.out.println("Votre attaque passe a " + hero.getAttaque());
                System.out.println(" ");
            }
        }
        if(pasEquiper){
            hero.getInventaire().getObjetsEquipes().add(this);
            hero.setAttaque(hero.getAttaque() + this.getAttaque());
            System.out.println("Vous venez d'equiper une epee");
            System.out.println(" ");
            System.out.println("Votre attaque passe a " + hero.getAttaque());
            System.out.println(" ");
        }
    }
}
