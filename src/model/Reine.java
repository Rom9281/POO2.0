package model;

public class Reine extends AbstractPieces {
	
	public Reine(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
	}
	
	public boolean specific(int xFinal, int yFinal){
		return (
				((this.coord.x==xFinal) && (this.coord.y!=yFinal))  || 
				((this.coord.x!=xFinal) && (this.coord.y==yFinal))  ||
				((Math.abs(this.coord.x-xFinal)/Math.abs(this.coord.y-yFinal)) == 1)); //
		}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}
}
