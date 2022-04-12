package model;

import java.util.LinkedList;
import java.util.List;

public class Echiquier implements BoardGames{
	private Jeu jeu_blanc;
	private Jeu jeu_noir;
	private Jeu jeu_courant;
	private Jeu jeu_attente;
	
	String message;
	
	
	public Echiquier() {
		super();
		jeu_blanc = new Jeu(Couleur.BLANC);
		jeu_noir = new Jeu(Couleur.NOIR);
		jeu_courant = jeu_blanc;
		jeu_attente = jeu_noir;
	}

	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
		
		boolean ret = false;
		
		ret= jeu_courant.isPieceHere(xInit,yInit) &&
				(xInit != xFinal || yInit != yFinal) &&
				jeu_courant.isMoveOk(xInit, yInit, xFinal, yFinal);

		
		return ret;
	}
	
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) 
	{
		boolean ret = false;
		
		if(this.isMoveOk(xInit, yInit, xFinal, yFinal)) 
		{

			if(jeu_attente.isPieceHere(xFinal, yFinal)) {jeu_attente.capture(xFinal, yFinal);}
			jeu_blanc.move(xInit, yInit, xFinal, yFinal);
			ret = true; // le mouvement est validé
		}
		
		return ret;
	}
	
	/**
	 * Recuperer l'ensemble des pieces IHM sur l'echequier.
	 * */
	public List<PieceIHM> getPiecesIHM(){
		List<PieceIHM> list = new LinkedList<PieceIHM>();  // Creation de la liste
		
		list.addAll(this.jeu_courant.getPiecesIHM()); // Ajout des pieces IHM de la liste courante
		list.addAll(this.jeu_attente.getPiecesIHM()); // Ajout des pieces IHM de la liste en attente
		
		return list;
		
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		Couleur ret = Couleur.NOIR;
		if(jeu_courant==jeu_blanc) {ret = Couleur.BLANC;}
		return ret;
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		return jeu_courant.getPieceColor(x, y);
	}
	
	public String toString() {
		return "Jeu Blanc : \n"+jeu_blanc.toString()+" || Jeu Noir : \n"+jeu_noir.toString();
	}
	
	public static void main(String[] args){
		Echiquier echiquier = new Echiquier();
		System.out.println(echiquier);
		
		System.out.println(echiquier.move(0,6,0,7));
	}
	
	/**
	 * Permet d'échanger le jeu actif
	 * */
	public void switchJoueur() 
	{
		if(jeu_courant==jeu_blanc) {
			jeu_courant=jeu_noir;
			jeu_attente=jeu_blanc;
		}
		else {
			jeu_courant=jeu_blanc;
			jeu_attente=jeu_noir;
		}
	}
	
	
}
