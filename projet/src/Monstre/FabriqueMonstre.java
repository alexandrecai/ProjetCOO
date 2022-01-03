package Monstre;

public class FabriqueMonstre {

    public FabriqueMonstre() {
    }

    public static Monstre creerMonstre(int type){
        if(type == 0) return new Zombie();
        else if (type == 1) return new Lapin();
        else if (type == 2) return new Doigby();

        return null;
    }
}
