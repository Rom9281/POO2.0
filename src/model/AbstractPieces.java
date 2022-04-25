package model;


public abstract class AbstractPieces implements Pieces {
	
	protected Coord coord;
	protected String nomPiece;
	protected Couleur couleur;
	
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
		return "Nom : "+this.getClass().getSimpleName()+" ; X : "+this.coord.x+" ; Y : "+this.coord.y+" ; Couleur : "+this.getCouleur();
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
	
	
	private static boolean checkCoord(int xFinal, int yFinal){
		return true;
	}
	
	abstract protected boolean specific(int xFinal, int yFinal);
	
	public boolean isMoveOk(int xFinal, int yFinal){
		
		return  ( checkCoord(xFinal, yFinal) && specific(xFinal, yFinal) ) || 
				( (xFinal==-1) && (yFinal==-1) )  ||
				( ! ( (this.coord.x==-1) && (this.coord.y==-1) ) );
		// permet le deplacement de la piece capturé si ses coordonnes ne sont pas deja a -1 -1
	}
	
	/**
	 * Permet le mouvement d'une piece
	 */
	public boolean move(int xFinal, int yFinal){
		boolean ret = false;
		
		if(this.isMoveOk(xFinal, yFinal)){ // TODO : la methode on board est telechargé pour tout les utilisateurs
			this.coord.x = xFinal;
			this.coord.y = yFinal;
			ret = true;
		}
		return ret;
	}
	
	/**
	 * 
	 * */
	public boolean capture()
	{
		return this.move(-1,-1);
	}
	
	public static void main(String argv[]){
		Pion piece = new Pion(Couleur.NOIR, new Coord(7,7));
		System.out.println(piece.toString());
		piece.move(7, 6);
		System.out.println(piece.toString());
		piece.move(7,7);
		System.out.println(piece.toString());
		piece.move(3, 4);
		System.out.println(piece.toString());
		piece.move(0, 2);
		System.out.println(piece.toString());
		
	}


}
