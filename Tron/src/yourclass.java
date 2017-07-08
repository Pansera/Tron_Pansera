import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
/**
 * Class qui gère le déplacement des joueurs
 * @author Vince
 *
 */
public class yourclass extends Core implements KeyListener, MouseListener,
		MouseMotionListener {
	/**
	 * //position du joueur 1
	 */
	int centrex1 = 40;
	int centrey1 = 40;		
	/**
	 * //position du joueur 2
	 */
	int centrex2 = 1560;
	int centrey2 = 860;
	/**
	 * 	//direction du joueur 1
	 */
	int currentDirection1 = 1;
	/**
	 * //direction du joueur 2
	 */
	int currentDirection2 = 3;
	/**
	 * //vitesse de déplacement
	 */
	int moveAmount = 5;		
	ArrayList<Integer> pathx1 = new ArrayList();
	ArrayList<Integer> pathy1 = new ArrayList();
	ArrayList<Integer> pathx2 = new ArrayList();
	ArrayList<Integer> pathy2 = new ArrayList();

	public void init() {
		super.init();

		Window w = sm.getFullScreenWindow();
		w.addKeyListener(this);
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		new yourclass().run();
	}
/**
 * 		//gestion du mouvement des joueurs (permet d'avancer)
 */
	public void draw(Graphics2D g) {	
		switch(currentDirection1){
		case 0:
			if (centrey1>0){
			centrey1-=moveAmount;
			} else {
				centrey1 = sm.getHeight();
			}
			break;
		case 1:
			if (centrex1 < sm.getWidth()){
			centrex1+=moveAmount;
			} else {
				centrex1 = 0;
			}
			break;
		case 2:
			if (centrey1 < sm.getHeight()){
			centrey1+=moveAmount;
			} else {
				centrey1 = 0;
			}
			break;
		case 3:
			if (centrex1>0){
			centrex1-=moveAmount;
			} else {
				centrex1 = sm.getWidth();
			}
			break;
		}
		switch(currentDirection2){
		case 0:
			if (centrey2>0){
			centrey2-=moveAmount;
			} else {
				centrey2 = sm.getHeight();
			}
			break;
		case 1:
			if (centrex2 < sm.getWidth()){
			centrex2+=moveAmount;
			} else {
				centrex2 = 0;
			}
			break;
		case 2:
			if (centrey2 < sm.getHeight()){
			centrey2+=moveAmount;
			} else {
				centrey2 = 0;
			}
			break;
		case 3:
			if (centrex2>0){
			centrex2-=moveAmount;
			} else {
				centrex2 = sm.getWidth();
			}
			break;
		}
	    for (int x = 0;x<pathx1.size();x++){
	    	if (((centrex1 == pathx1.get(x)) && (centrey1 == pathy1.get(x))) || ((centrex2 == pathx2.get(x)) && (centrey2 == pathy2.get(x))) || ((centrex1 == pathx2.get(x)) && (centrey1 == pathy2.get(x))) || ((centrex2 == pathx1.get(x)) && (centrey2 == pathy1.get(x)))){
	    		System.exit(0);
	    	}
	    }
		pathx1.add(centrex1);
		pathy1.add(centrey1);
		pathx2.add(centrex2);
		pathy2.add(centrey2);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
		for (int x = 0;x<pathx1.size();x++){
		g.setColor(Color.green);
		g.fillRect(pathx1.get(x), pathy1.get(x), 10, 10);
		g.setColor(Color.red);
		g.fillRect(pathx2.get(x), pathy2.get(x), 10, 10);
		}
	}

	public void keyPressed(KeyEvent e) {
		/*if (e.getKeyCode() == KeyEvent.VK_UP) {		//changement de direction du J1 avec 4 touches
			if (currentDirection1 != 2){
			currentDirection1 = 0;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (currentDirection1 != 0){
				currentDirection1 = 2;
				}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (currentDirection1 != 3){
				currentDirection1 = 1;
				}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (currentDirection1 != 1){
				currentDirection1 = 3;
				}
		}*/
		/**
		 * //Changement de direction du J2 avec 2 touches
		 */
						if(e.getKeyCode() == KeyEvent.VK_A)	
							{
							if(currentDirection1 == 0){
							currentDirection1 = 3;
							}
							else if(currentDirection1 == 1){
								currentDirection1 = 0;
							}
							else if(currentDirection1 == 2){
								currentDirection1 = 1;
							}
							else if(currentDirection1 == 3){
								currentDirection1 = 2;
							}
							}
						if(e.getKeyCode() == KeyEvent.VK_Z)
						{
							if(currentDirection1 == 0){
								currentDirection1 = 1;
							}
							else if(currentDirection1 == 1){
								currentDirection1 = 2;
							}
							else if(currentDirection1 == 2){
								currentDirection1 = 3;
							}
							else if(currentDirection1 == 3){
								currentDirection1 = 0;
							}
		}
		/*if (e.getKeyCode() == KeyEvent.VK_Z){		//changement de direction du J2 avec 4 touches
			if (currentDirection2 != 2){
			currentDirection2 = 0;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			if (currentDirection2 != 0){
				currentDirection2 = 2;
				}
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			if (currentDirection2 != 3){
				currentDirection2 = 1;
				}
		} else if (e.getKeyCode() == KeyEvent.VK_Q) {
			if (currentDirection2 != 1){
				currentDirection2 = 3;
				}
		}*/
	/**
	 * //Changement de direction du J2 avec 2 touches
	 */
															if(e.getKeyCode() == KeyEvent.VK_O)	
															{
																if(currentDirection2 == 0){
																	currentDirection2 = 3;
																	}
																else if(currentDirection2 == 1){
																	currentDirection2 = 0;
																}
																else if(currentDirection2 == 2){
																	currentDirection2 = 1;
																}
																else if(currentDirection2 == 3){
																	currentDirection2 = 2;
																}
															}
															if(e.getKeyCode() == KeyEvent.VK_P)
															{
																if(currentDirection2 == 0){
																	currentDirection2 = 1;
																	}
																else if(currentDirection2 == 1){
																	currentDirection2 = 2;
																}
																else if(currentDirection2 == 2){
																	currentDirection2 = 3;
																}
																else if(currentDirection2 == 3){
																	currentDirection2 = 0;
																}
															}
	}
/**
 * Gestionnaire d'évènement clavier
 */
	public void keyReleased(KeyEvent e) {

	}
	/**
	 * Gestionnaire d'évènement clavier
	 */
	public void keyTyped(KeyEvent arg0) {

	}
/**
 * Gère des évènements à la souris 
 * (inutile dans mon cas puisque je n'utilise pas la souris, mais ça pourrait servir si on veut faire évoluer le jeu)
 */
	public void mouseClicked(MouseEvent e) {

	}
	/**
	 * Gère des évènements à la souris 
	 * (inutile dans mon cas puisque je n'utilise pas la souris, mais ça pourrait servir si on veut faire évoluer le jeu)
	 */
	public void mouseEntered(MouseEvent arg0) {
	}
	/**
	 * Gère des évènements à la souris 
	 * (inutile dans mon cas puisque je n'utilise pas la souris, mais ça pourrait servir si on veut faire évoluer le jeu)
	 */
	public void mouseExited(MouseEvent arg0) {
	}
	/**
	 * Gère des évènements à la souris 
	 * (inutile dans mon cas puisque je n'utilise pas la souris, mais ça pourrait servir si on veut faire évoluer le jeu)
	 */
	public void mousePressed(MouseEvent e) {
	}
	/**
	 * Gère des évènements à la souris 
	 * (inutile dans mon cas puisque je n'utilise pas la souris, mais ça pourrait servir si on veut faire évoluer le jeu)
	 */
	public void mouseReleased(MouseEvent e) {
	}
	/**
	 * Gère des évènements à la souris 
	 * (inutile dans mon cas puisque je n'utilise pas la souris, mais ça pourrait servir si on veut faire évoluer le jeu)
	 */
	public void mouseDragged(MouseEvent e) {

	}
	/**
	 * Gère des évènements à la souris 
	 * (inutile dans mon cas puisque je n'utilise pas la souris, mais ça pourrait servir si on veut faire évoluer le jeu)
	 */
	public void mouseMoved(MouseEvent e) {

	}
}
