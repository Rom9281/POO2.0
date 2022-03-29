package model;

public class Echiquier implements BoardGames{
	private Jeu jeu_blanc;
	private Jeu jeu_noir;
	boolean jeu_blanc_courant;
	String message;
	
	
	public Echiquier() {
		super();
		jeu_blanc = new Jeu(Couleur.BLANC);
		jeu_noir = new Jeu(Couleur.NOIR);
		jeu_blanc_courant = true;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){
		boolean ret = false;
		if(
				(jeu_blanc.isPieceHere(xInit,yInit))&&
				(jeu_noir.isPieceHere(xInit,yInit)))
		{
				if(
						(xInit != xFinal)&&
						(yInit != yFinal))
				{
					ret = true;
				}
		}
		return ret;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Couleur getPieceColor(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return "Jeu Blanc : \n"+jeu_blanc.toString()+" || Jeu Noir : \n"+jeu_noir.toString();
	}
	
	public static void main(String[] args){
		Echiquier echiquier = new Echiquier();
		System.out.println(echiquier);
	}
	
	private void switchJoueur() {
		jeu_blanc_courant = !jeu_blanc_courant;
	}
	
	
}
