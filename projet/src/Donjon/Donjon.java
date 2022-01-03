package Donjon;


import java.util.ArrayList;
import java.util.Random;

public class Donjon {

    Random r = new Random();
    int nbPieces = r.nextInt(6) + 1;
    ArrayList<Piece> pieces = new ArrayList<Piece>();

    public Donjon(){
        for (int i = 0; i < nbPieces; i++) {
            pieces.add(new Piece());
        }
    }

    public Piece getPiece(int indice){
        return pieces.get(indice);
    }

    public ArrayList<Piece> getListePieces() {
        return pieces;
    }
}
