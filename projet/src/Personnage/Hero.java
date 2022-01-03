package Personnage;

public abstract class Hero {

    String nom;
    int vie;
    int defence;
    int attaque;

    public Hero(String nom, int vie, int defence, int attaque) {
        this.nom = nom;
        this.vie = vie;
        this.defence = defence;
        this.attaque = attaque;
    }

    public String getNom() {
        return nom;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }
}
