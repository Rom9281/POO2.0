package model;

import java.awt.Point;

public abstract class AbstractPieces implements Pieces {
	
	protected Coord coord;
	protected String nomPiece;
	private Couleur couleur_de_piece;
	
	/**
	 * Constructeurs
	 */
	public AbstractPieces(Couleur couleur_de_piece, Coord coord)
	{
		this.coord = new Coord(coord.x,coord.y);
		this.couleur_de_piece = couleur_de_piece;
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
		return "Nom : ; X : "+this.coord.x+"; Y : "+this.coord.y;
	}

}
