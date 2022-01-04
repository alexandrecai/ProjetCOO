package Inventaire;

import Personnage.Hero;

public interface Equipement {

    public void equiper(Hero hero);

    public double getDefence();

    public String getType();

    public String getPuissance();
}
