package model;

public class Cavalier extends AbstractPieces {
	
	public Cavalier(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
	}
	
	public boolean specific(int xFinal, int yFinal){
		return (
				((Math.abs(this.coord.x-xFinal)==1) && (Math.abs(this.coord.y-yFinal)==2)) || 
				((Math.abs(this.coord.x-xFinal)==2) && (Math.abs(this.coord.y-yFinal)==3))); //
		}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}
}
