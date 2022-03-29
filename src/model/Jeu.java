package model;

import java.util.LinkedList;
import java.util.List;
import tools.ChessPiecesFactory;

public class Jeu {
	private Couleur couleur;
	private List<Pieces> listePieces;
	
	public Jeu(Couleur couleur) {
		this.couleur = couleur;
		this.listePieces = ChessPiecesFactory.newPieces(couleur);
	}
	
	public String toString(){
		String retString = "";
		
		for (Pieces pieces : listePieces) {
			retString += " - "+pieces.toString()+"\n";
		}
		
		return retString;
	}
	
	private Pieces findPieces(int x, int y) {
		
		for (Pieces piece : listePieces) {
			if((piece.getX() == x) && (piece.getY() == y)) {
				return piece;
			}
		}
		
		return null;
	}
	
	public boolean isPieceHere(int x,int y){
		return (this.findPieces(x, y) != null);
	}
	
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
