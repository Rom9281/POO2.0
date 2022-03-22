package model;

public class Roi extends AbstractPieces {
	
	public Roi(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
	}
	
	public boolean specific(int xFinal, int yFinal){
		return (
				(Math.abs(this.coord.x-xFinal)==1) || 
				(Math.abs(this.coord.y-yFinal)==1) ); 
		}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}
}
