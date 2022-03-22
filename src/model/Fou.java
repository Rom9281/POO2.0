package model;

public class Fou extends AbstractPieces {
	
	public Fou(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
	}
	
	public boolean specific(int xFinal, int yFinal){
		return ((Math.abs(this.coord.x-xFinal) == Math.abs(this.coord.y-yFinal))); //
		}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}
}
