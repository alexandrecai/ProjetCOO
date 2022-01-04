package Inventaire.Equipements;

import Inventaire.Equipement;
import Personnage.Hero;

import java.util.Random;

public class Armure implements Equipement{

    double defence;
    String type;
    String puissance;
    Random r = new Random();

    public Armure() {
        this.type = "armure";
        int rarete = r.nextInt(3) + 1;

        if(rarete == 1){
            this.defence = 0.1;
            this.puissance = rarete + " etoile";
        }
        else if(rarete == 2){
            this.defence = 0.15;
            this.puissance = rarete + " etoiles";
        }
        else {
            this.defence = 0.2;
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
    public void equiper(Hero hero) {
        Boolean pasEquiper = true;
        for (Equipement e: hero.getInventaire().getObjetsEquipes()){
            if(e.getType().equals(this.getType())){
                hero.getInventaire().getObjetsEquipes().remove(e);
                hero.getInventaire().getObjetsEquipes().add(this);
                hero.setDefence(hero.getDefence() - e.getDefence());
                hero.setDefence(Math.round(hero.getDefence() + this.getDefence()));
                pasEquiper = false;
                System.out.println("Vous venez de remplacer votre armure");
                System.out.println("Votre defence passe a " + hero.getDefence());
            }
        }
        if(pasEquiper){
            hero.getInventaire().getObjetsEquipes().add(this);
            hero.setDefence(Math.round(hero.getDefence() + this.getDefence()));
            System.out.println("Vous venez de mettre une armure");
            System.out.println("Votre defence passe a " + hero.getDefence());
        }
    }
}
