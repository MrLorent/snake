package td7;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

//On cr�er une class qui permettera de g�rer l'affichage via un thread
public class ZoneDeJeu extends JPanel implements Runnable {
	private Terrain leTerrain;
	private Serpent leSerpent;
	private Thread onJoue;
	
	//On construit la zone de jeu en construisant ind�pendement un terrain avec de la nourriture et un serpent
	public ZoneDeJeu(int qte, int largeur, int hauteur) {
		leTerrain = new Terrain(qte, largeur, hauteur);
		leSerpent = new Serpent(largeur, hauteur);
		
		//On cr�er un thread
		onJoue = new Thread(this);
		//Que l'on lance et dont on g�re le fonctionnement dans la m�thode run
		onJoue.start();
	}
	
	//Fonction permettant de transmettre les interactions r�cup�r�es dans la fen�tre principale "jeu"
	public void transDirection(int nvlleDirection) {
		leSerpent.setDirection(nvlleDirection);
	}
	
	//On d�finit la fonction paint qui permettera de dessiner les diff�rents �l�ments
	public void paint(Graphics gc) {
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, 1000, 1000);
		leTerrain.seDessine(gc);
		leSerpent.seDessine(gc);
	}

	@Override
	//C'est la m�thode qui d�finit le fonctionnement du thread
	public void run() {
		boolean finJeu = false;
		// Il faut faire une boucle pour un d�placement continu
		while (!finJeu) {
			leSerpent.seDeplace();
			if(leSerpent.seMordLaQueue()) {
				finJeu = true; // Il faut utiliser un bool�en et ne pas interrompre brutalement le processus
				
			}
			if(leTerrain.contientNourriture(leSerpent.positionTete().width,leSerpent.positionTete().height)) {
				leTerrain.supprimerNourriture(leSerpent.positionTete().width,leSerpent.positionTete().height);
				leSerpent.sAgrandit();
			}
			//On appel la fonction paint d�finit plus haut
			repaint();
		
			try {
				//On "endort" quelque peu le thread de mani�re � avoir un affichage pas trop rapide
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
