package GestionJeu;


import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        ControleurJeu controleur = new ControleurJeu();
        controleur.lancementJeu();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Relancer une partie ?");
        String reponse = scanner.nextLine();
        System.out.println(" ");
        while (reponse.equalsIgnoreCase("oui")){
            controleur = new ControleurJeu();
            controleur.lancementJeu();
            System.out.println("Relancer une partie ?");
            reponse = scanner.nextLine();
            System.out.println(" ");
        }
        System.out.println("Vous quittez le jeu");
    }
}
