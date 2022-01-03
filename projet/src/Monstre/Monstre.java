package Monstre;

public abstract class Monstre {

    int vie;
    int degat;
    int vitesse;
    String type;

    public int getVie() {
        return vie;
    }

    public String getType() {
        return type;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getDegat() {
        return degat;
    }
}
