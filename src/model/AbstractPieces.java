package model;


public abstract class AbstractPieces implements Pieces {
	
	protected Coord coord;
	protected String nomPiece;
	private Couleur couleur;
	
	/**
	 * Constructeurs
	 */
	public AbstractPieces(Couleur couleur_de_piece, Coord coord)
	{
		this.coord = new Coord(coord.x,coord.y);
		this.couleur = couleur_de_piece;
	}
	
	public AbstractPieces(Coord coord)
	{
		this(Couleur.NOIR,coord);
	}
	
	public AbstractPieces(Couleur couleur_de_piece) {
		this(couleur_de_piece,new Coord(0, 0));
	}
	
	
	@Override
	public String toString() {
		return "Nom : ; X : "+this.coord.x+"; Y : "+this.coord.y+" ";
	}
	
	public int getX(){
		return this.coord.x;
	}
	
	public int getY(){
		return this.coord.y;
	}
	
	public Couleur getCouleur(){
		return couleur;
	}
	
	public boolean onBoard(int xFinal, int yFinal){
		return false;
	}
	
	
	public boolean move(int xFinal, int yFinal){
		boolean ret = false;
		
		if(this.isMoveOk(xFinal, yFinal)){
			this.coord.x = xFinal;
			this.coord.y = yFinal;
			ret = true;
		}
		return ret;
	}
	
	public static void main(String argv[]){
		Tour tour = new Tour(Couleur.BLANC, new Coord(0,0));
		System.out.println(tour.toString());
		
	}


}
