package Personnage;

public abstract class Hero {

    String nom;
    int vie;
    double defence;
    int attaque;
    int vitesse;

    public Hero(String nom, int vie, double defence, int attaque, int vitesse) {
        this.nom = nom;
        this.vie = vie;
        this.defence = defence;
        this.attaque = attaque;
        this.vitesse = vitesse;
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

    public int getVitesse() {
        return vitesse;
    }

    public int getAttaque() {
        return attaque;
    }

    public double getDefence() {
        return defence;
    }
}
