package Inventaire.Equipements;

import Inventaire.Equipement;
import Personnage.Hero;

import java.util.Random;

public class Botte implements Equipement{

    double defence;
    int attaque;
    String type;
    String puissance;
    Random r = new Random();

    public Botte() {
        this.type = "bottes";
        int rarete = r.nextInt(3) + 1;

        if(rarete == 1){
            this.defence = 0.025;
            this.puissance = rarete + " etoile";
        }
        else if(rarete == 2){
            this.defence = 0.05;
            this.puissance = rarete + " etoiles";
        }
        else {
            this.defence = 0.075;
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
                hero.setDefence(hero.getDefence() - e.getDefence());
                hero.setDefence(hero.getDefence() + this.getDefence());
                pasEquiper = false;
                System.out.println("Vous venez de remplacer vos bottes");
                System.out.println(" ");
                System.out.println("Votre defence passe a " + hero.getDefence());
                System.out.println(" ");
            }
        }
        if(pasEquiper){
            hero.getInventaire().getObjetsEquipes().add(this);
            hero.setDefence(hero.getDefence() + this.getDefence());
            System.out.println("Vous venez de mettre des bottes");
            System.out.println(" ");
            System.out.println("Votre defence passe a " + hero.getDefence());
            System.out.println(" ");
        }
    }
}
