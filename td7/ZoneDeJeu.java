package td7;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

//On créer une class qui permettera de gérer l'affichage via un thread
public class ZoneDeJeu extends JPanel implements Runnable {
	private Terrain leTerrain;
	private Serpent leSerpent;
	private Thread onJoue;
	
	//On construit la zone de jeu en construisant indépendement un terrain avec de la nourriture et un serpent
	public ZoneDeJeu(int qte, int largeur, int hauteur) {
		leTerrain = new Terrain(qte, largeur, hauteur);
		leSerpent = new Serpent(largeur, hauteur);
		
		//On créer un thread
		onJoue = new Thread(this);
		//Que l'on lance et dont on gère le fonctionnement dans la méthode run
		onJoue.start();
	}
	
	//Fonction permettant de transmettre les interactions récupérées dans la fenêtre principale "jeu"
	public void transDirection(int nvlleDirection) {
		leSerpent.setDirection(nvlleDirection);
	}
	
	//On définit la fonction paint qui permettera de dessiner les différents éléments
	public void paint(Graphics gc) {
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, 1000, 1000);
		leTerrain.seDessine(gc);
		leSerpent.seDessine(gc);
	}

	@Override
	//C'est la méthode qui définit le fonctionnement du thread
	public void run() {
		boolean finJeu = false;
		// Il faut faire une boucle pour un déplacement continu
		while (!finJeu) {
			leSerpent.seDeplace();
			if(leSerpent.seMordLaQueue()) {
				finJeu = true; // Il faut utiliser un booléen et ne pas interrompre brutalement le processus
				
			}
			if(leTerrain.contientNourriture(leSerpent.positionTete().width,leSerpent.positionTete().height)) {
				leTerrain.supprimerNourriture(leSerpent.positionTete().width,leSerpent.positionTete().height);
				leSerpent.sAgrandit();
			}
			//On appel la fonction paint définit plus haut
			repaint();
		
			try {
				//On "endort" quelque peu le thread de manière à avoir un affichage pas trop rapide
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
