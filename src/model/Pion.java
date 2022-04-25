package model;

public class Pion extends AbstractPieces implements Pions{
	
	public Pion(Couleur couleur_de_piece, Coord coord){
		super(couleur_de_piece,coord);
	}
	
	public boolean specific(int xFinal, int yFinal){
		int pas = 1;
		if(this.couleur == Couleur.BLANC) {pas = -1;} // TODO : attention au NOIRBLANC!
		return (
				( (this.coord.y+pas==yFinal) || (this.coord.y+(2*pas)==yFinal) ) &&
				(this.coord.x==xFinal)
				); //
		}
	
	// TODO : finish
	public boolean isMoveDiagOk(int xFinal,int yFinal) {
		return true;
	};

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}
}
