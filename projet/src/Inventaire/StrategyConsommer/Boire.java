package Inventaire.StrategyConsommer;

import Personnage.Hero;

public class Boire implements ConsommerStrategy{

    @Override
    public void consommer(int soin, Hero hero) {
        if((hero.getVie() + soin) >= 100){
            hero.setVie(100);
        }
        else {
            hero.setVie(hero.getVie()+soin);
        }
        System.out.println("Vous buvez une potion");
        System.out.println(" ");
        System.out.println("Vous avez desormais " + hero.getVie() + " hp");
        System.out.println(" ");
    }
}
