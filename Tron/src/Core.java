import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
/**
 * Class Main dans laquelle on gère les thread,
 * on commande les affichages,
 * et on dis au jeu quand il doit commencer/ s'arreter et ce qu'il doit faire entre les deux
 * @author Vince
 *
 */
public abstract class Core {
	/**
	 * Liste des résolutions d'écran compatible avec le jeu, libre à nous d'en rajouter
	 */
	private static final DisplayMode modes[] = 
		{
		//new DisplayMode(1920,1080,32,0),
		new DisplayMode(1680,1050,32,0),
		//new DisplayMode(1280,1024,32,0),
		new DisplayMode(1600,900,32,0),
		new DisplayMode(800,600,32,0),

		};
	private boolean running;
	protected ScreenManager sm;
	/**
	 * Méthode qui sert à dire quand le jeu s'arrete
	 */
	public void stop(){
		
		running = false;
	}
	/**
	 * Méthode qui fait tourner le jeu (c'est une boucle qui ne s'arrete que quand le jeu s'arrete)
	 */
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			
			sm.restoreScreen();
		}
	}
	/**
	 * Méthode d'initialisation
	 * -Création de la fenêtre
	 * -Dis au programme que le jeu commence
	 */
	public void init(){
		sm = new ScreenManager();
		DisplayMode dm = sm.findFirstCompatibaleMode(modes);
		sm.setFullScreen(dm);
		Window w = sm.getFullScreenWindow();
		w.setFont(new Font("Arial",Font.PLAIN,20));
		w.setBackground(Color.WHITE);
		w.setForeground(Color.RED);
		w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null")); 
		running = true;
	}
	/**
	 * Boucle dans laquelle:
	 * -On rafraichit l'affichage (à l'aide d'un compteur currentTimeMillis())
	 * -On rafraichit la position et la direction des joueurs
	 * -On "dessine" les murs
	 */
	public void gameLoop(){
		long startTime = System.currentTimeMillis();
		long cumTime = startTime;
		
		while (running){
			long timePassed = System.currentTimeMillis()-cumTime;
			cumTime+= timePassed;
			update(timePassed);
			Graphics2D g = sm.getGraphics();
			draw(g);
			g.dispose();
			sm.update();
			
			try{
				/**
				 * temps de rafraichissement
				 */
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}
	
	public void update(long timePassed){}
	
	public abstract void draw(Graphics2D g);
	
}
