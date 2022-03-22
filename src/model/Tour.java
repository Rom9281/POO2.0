package model;

public class Tour extends AbstractPieces {
	
	public Tour(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
	}
	
	public boolean isMoveOk(int xFinal, int yFinal){
		return false;
	}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}
}
