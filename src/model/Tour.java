package model;

public class Tour extends AbstractPieces {
	
	public Tour(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
	}
	
	public boolean specific(int xFinal, int yFinal){
		return (
				((this.coord.x==xFinal) && (this.coord.y!=yFinal)) || 
				((this.coord.x!=xFinal) && (this.coord.y==yFinal))); //
		}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}
}
