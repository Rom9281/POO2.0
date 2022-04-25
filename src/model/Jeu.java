package model;

import java.util.LinkedList;
import java.util.List;
import tools.ChessPiecesFactory;

public class Jeu {
	private Couleur couleur;
	private List<Pieces> listePieces;
	private boolean castling;
	
	public Jeu(Couleur couleur) {
		this.couleur = couleur;
		this.listePieces = ChessPiecesFactory.newPieces(couleur);
		this.castling = false;
	}
	
	public String toString(){
		String retString = "";
		
		for (Pieces pieces : listePieces) {
			retString += " - "+pieces.toString()+"\n";
		}
		
		return retString;
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
		boolean ret = false;
		
		if(isPieceHere(xInit,yInit)){ // Si il y a une piece : necessaire car find piece part du principe qu'il y a une piece
			Pieces piece = findPieces(xInit, yInit); // trouver la piece
			ret = piece.isMoveOk(xFinal, yFinal); // retourner la piece
		}
		return ret;
	}
	
	public boolean move(int xInit, int yInit, int xFinal, int yFinal)
	{
		boolean ret = false;
		
		if(this.isMoveOk(xInit, yInit, xFinal, yFinal))
		{
			Pieces pieces = findPieces(xInit, yInit); // recupere la piece
			pieces.move(xFinal, yFinal); // bouger la piece
			ret = true; // valider
		}
		
		return ret;
	}
	
	private Pieces findPieces(int x, int y) {
		
		for (Pieces piece : listePieces) {
			if((piece.getX() == x) && (piece.getY() == y)) {
				return piece;
			}
		}
		
		return null;
	}
	
	/**
	 * Permet la capture d'un pion
	 * */
	public boolean capture(int xCatch,int yCatch)
	{
		// TODO : code l'aspect capture du jeu
		boolean ret = false;
		
		return ret;
	}
	
	public boolean isPieceHere(int x,int y){
		return (this.findPieces(x, y) != null);
	}
	
	/**
	 * Obtenir la couleur de la piece si elle est au coord données
	 * @param x : coord x de la piece
	 * @param y : coord y de la piece
	 * @return couleur : la couleur de la piece ou null si n'est pas la
	 * */
	public Couleur getPieceColor(int x, int y) {
		
		Couleur ret = null;
		
		if(isPieceHere(x,y)){ // Si il y a une piece : necessaire car find piece part du principe qu'il y a une piece
			Pieces piece = findPieces(x,y); // trouver la piece
			ret = piece.getCouleur();
		}
		return ret;
	}
	
	/**
	 * Recupere la position du roi dans le jeu
	 * */
	public Coord getKingCoord() 
	{
		
		for (Pieces piece : listePieces)  // parcours le jeu
		{
			if(piece.getClass().getSimpleName()=="Roi") {return new Coord(piece.getX(),piece.getY());} // si il y a un roi, retourner ses coordonnées
		}
		
		return null;
	}
	
	/**
	 * Permet la promotion du pion
	 * TODO : Arriver a changer le type de l'objet.
	 * */
	public boolean pawnPromotion(int xFinal,int yfinal,java.lang.String type) 
	{
		Pieces piece = this.findPieces(xFinal,yfinal);
		
		if(piece!=null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Recuperer la liste des pieces sous forme de PiecesIHM
	 * @return
	 */
	public List<PieceIHM> getPiecesIHM(){               
		PieceIHM newPieceIHM = null;               
		List<PieceIHM> list = new LinkedList<PieceIHM>(); 
		for (Pieces piece : listePieces){ 
			boolean existe = false; 
			
			// si le type de piece existe déjà dans la liste de PieceIHM
			// ajout des coordonnées de la pièce dans la liste de Coord de ce type 
			// si elle est toujours en jeu (x et y != -1)
			for ( PieceIHM pieceIHM : list){                                   
				if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){ 
					existe = true; 
					if (piece.getX() != -1){ 
						pieceIHM.add(new Coord(piece.getX(), piece.getY()));      
						}     
					}       
				} 
			// sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
			if (! existe) { 
				if (piece.getX() != -1){     
					newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(), piece.getCouleur()); 
					newPieceIHM.add(new Coord(piece.getX(), piece.getY())); 
					list.add(newPieceIHM);
					}    
				}   
			} 
		return list;
		}  
		
}
