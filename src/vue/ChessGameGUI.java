package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import model.observable.ChessGame;
import tools.ChessImageProvider;
import tools.ChessPiecePos;

 
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener {
	private JPanel chessBoard;
	private JLabel chessPiece;
	private Container parent_container;
	private JLayeredPane layeredPane;
  
	private ChessGameControlers chess_game_controler;
	private int xAdjustment;
	private int yAdjustment;
	private Coord init_coord;
	
	public ChessGameGUI(String name,ChessGameControlers controler, Dimension boardSize)
	  {
		// TODO : faire le board size
		// TODO : faire le name
		this.chess_game_controler = controler;
		init_coord = new Coord(0,0);
		
		//  Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		
		//   Add a chess board to the Layered Pane 
		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout( new GridLayout(8, 8) );
		chessBoard.setPreferredSize( boardSize );
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
		
		for (int i = 0; i < 64; i++) 
		{
			JPanel square = new JPanel( new BorderLayout() );
			chessBoard.add( square );
			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
			else
				square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
			}
		
		//Add a few pieces to the board
		for (int i = 0; i < ChessPiecePos.values().length; i++) 
		{
	
	      Couleur pieceCouleur = ChessPiecePos.values()[i].couleur;
	
	      for (int j = 0; j < (ChessPiecePos.values()[i].coords).length; j++) 
	      {
	                String className = ChessPiecePos.values()[i].nom;     // attention au chemin
	                Coord pieceCoord = ChessPiecePos.values()[i].coords[j];
	                JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(className, pieceCouleur)) );
	                JPanel panel = (JPanel)chessBoard.getComponent(pieceCoord.x + pieceCoord.y*8);
	                panel.add(piece); 
	      }
	    }
	  }
 
  public void mousePressed(MouseEvent e)
  {
	  chessPiece = null;
	  
	  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
	  
	  this.parent_container = c.getParent();
	 
	  if (c instanceof JPanel) {return;}
	  
	  Point parentLocation = c.getParent().getLocation();
	  // chess_game.getColorCurrentPlayer() != chess_game.getPieceColor(parentLocation.x/75, parentLocation.y/75)
	  
	  this.init_coord = new Coord(parentLocation.x/75, parentLocation.y/75);
	  
	  // Is player ok demande un coord et non un point... c'est assez contre intuitif
	  if(!chess_game_controler.isPlayerOK(this.init_coord)) 
	  {
		  System.out.println("Wrong piece");
		  return;
		  
	  } // Si la piece n'est pas de la bonne couleur, on annule.
	  
	  xAdjustment = parentLocation.x - e.getX();
	  yAdjustment = parentLocation.y - e.getY();
	  
	  chessPiece = (JLabel)c;
	  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
	  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
  }
 
  //Move the chess piece around
  
  public void mouseDragged(MouseEvent me) 
  {
	  if (chessPiece == null) return;
	  chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	  
  }
  
 
  //Drop the chess piece back onto the chess board
 
  public void mouseReleased(MouseEvent e) {
	  if(chessPiece == null) return;
	 
	  chessPiece.setVisible(false);
	  
	  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
	  
	  Point parentLocation = c.getParent().getLocation();
	  
	  chess_game_controler = (ChessGameControler) chess_game_controler;
	  
	  if(chess_game_controler.move(this.init_coord, new Coord(c.getX()/75, c.getY()/75))) {
		  if (c instanceof JLabel){
			  Container parent = c.getParent();
			  
			  parent.remove(0);
			  parent.add( chessPiece );
		  }
		  else {
			  Container parent = (Container)c;
			  parent.add( chessPiece );
		  }
		  
	  }
	  else 
	  {
		  this.parent_container.add( chessPiece );
	  }
	  
	  chessPiece.setVisible(true);
	  
  }
 
  public void mouseClicked(MouseEvent e) {
  
  }
  public void mouseMoved(MouseEvent e) {
 }
  public void mouseEntered(MouseEvent e){
  
  }
  public void mouseExited(MouseEvent e) {
  
  }
  
  public void update(Observable arg0, Object arg1) {

		List<PieceIHM> piecesIHM = (List<PieceIHM>) arg1;
		
		for(PieceIHM pieceIHM : piecesIHM) {
            JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(pieceIHM.getTypePiece(), pieceIHM.getCouleur())) );
            JPanel panel = (JPanel)chessBoard.getComponent(pieceIHM.get + pieceCoord.y*8);
            panel.add(piece); 
			
		}
		/*

		//String[][] damier = new String[8][8];
		
		// création d'un tableau 2D avec les noms des pièces
		for(PieceIHM pieceIHM : piecesIHM) {

			Couleur color = pieceIHM.getCouleur();
			
			Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
			
			for(Coord coord : pieceIHM.getList()) {
				damier[coord.y][coord.x] = stColor + type;
			}			
		}
		
		System.out.println(st);	
		*/	
	}
	
}

 /*
  public static void main(String[] args) 
  {
	  
  ChessGame model;
  ChessGameControler controler;
  
  model = new ChessGame();	
  controler = new ChessGameControler(model);
  
  JFrame frame = new ChessGameGUI(controler);
  
  frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
  frame.pack();
  frame.setResizable(true);
  frame.setLocationRelativeTo( null );
  frame.setVisible(true);
 }

*/
