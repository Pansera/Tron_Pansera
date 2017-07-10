
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Classe qui gère l'affichage de la fenêtre 
 * (Vu qu'ici on utilise le Fullscreen ça demande plus de précautions, comme la méthode getCompatibleDisplayModes)
 * @author Vince
 *
 */
public class ScreenManager {
	
	private GraphicsDevice vc;
	
	public ScreenManager(){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice();
	}
	/**
	 * Récupère la résolution de l'écran
	 * @return
	 */
	public DisplayMode[] getCompatibleDisplayModes(){	
		return vc.getDisplayModes();
	}
	
	public DisplayMode findFirstCompatibaleMode(DisplayMode[] modes){
		
		DisplayMode goodModes[] = vc.getDisplayModes();
		for(int x = 0; x<modes.length;x++){
			for(int y = 0;y<goodModes.length;y++){
				if(displayModesMatch(modes[x],goodModes[y])){
					return modes[x];
				}
			}
		}
		return null;
	}
	
	public DisplayMode getCurrentDM(){
		return vc.getDisplayMode();
	}
	
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
		if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
			return false;
		}
		if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()){
			return false;
		}
		if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()){
			return false;
		}
		return true;
	}
	/**
	 * Définition des caractéristiques de notre fenetre
	 * @param dm
	 */
	public void setFullScreen(DisplayMode dm){
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		vc.setFullScreenWindow(f);
		
		if(dm != null && vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
			}catch(Exception ex){}
			f.createBufferStrategy(2);
		}
	}
	/**
	 * Mise en place des buffers
	 * @return
	 */
	public Graphics2D getGraphics(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			BufferStrategy bs = w.getBufferStrategy();
			return (Graphics2D)bs.getDrawGraphics();
		}
		else{
			return null;
		}
	}
	/**
	 * Mise à jour de l'affichage à l'écran
	 */
	public void update(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			BufferStrategy bs = w.getBufferStrategy();
			if(!bs.contentsLost()){
				bs.show();
			}
		}
	}
	/**
	 * Déclaration d'une nouvelle Fenetre de type Windows
	 * @return
	 */
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}
	/**
	 * Récupère la largeur de l'écran
	 * @return
	 */
	public int getWidth(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			return w.getWidth();
		}else{
			return 0;
		}
	}
	/**
	 * Récupère la largeur de l'écran
	 * @return
	 */
	public int getWidth(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			return w.getWidth();
		}else{
			return 0;
		}
	}
	/**
	 * Récupère la hauteur de l'écran
	 * @return
	 */
	public int getHeight(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			return w.getHeight();
		}else{
			return 0;
		}
	}
	/**
	 * Méthode qui ferme la fenetre
	 */
	public void restoreScreen(){
		Window w = vc.getFullScreenWindow();
		if(w != null){
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
	/**
	 * Génère une image adaptée à la définition de l'écran
	 * @param w
	 * @param h
	 * @param t
	 * @return
	 */
	public BufferedImage createCompatibaleimage(int w, int h, int t){
			Window win = vc.getFullScreenWindow();
			if(win != null){
				GraphicsConfiguration gc = win.getGraphicsConfiguration();
				return gc.createCompatibleImage(w,h,t);
			}else{
				return null;
			}
		
		}
	
}
