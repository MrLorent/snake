package td7;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

//On déclare la classe comme KeyListener pour pouvoir récupérer les interractions clavier
public class Jeu extends JFrame implements KeyListener {
	private Choix nvxChoix;
	
	public Jeu() {
		//On gère l'ouverture/fermeture de la fenêtre		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//On lui donne un titre
		setTitle("Application TD7");
		//On lui donne une taille bloquée
		setSize (new Dimension (400, 300));
		
		//On appel le constructeur de panneau de choix
		Choix panChoix = new Choix(this);
		//On l'ajoute à la fenêtre principale "jeu"
		getContentPane().add(panChoix);
		
		//On créer un nouveau choix afin de passer les interactions claviers aux autre fenêtres
		nvxChoix = new Choix(this);
		//On l'ajoute à la fenêtre principale "Jeu"
		getContentPane().add(nvxChoix);
		//On affiche les composants graphiques
		setVisible(true);
		
	}
	
	public static void main (String[] args) {
		//Le main sert seulement à lancer le constructeur de l'application jeu
		new Jeu();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// On met des écouteurs sur les touches qui nous intéresses afin de gérer les interractions clavier
		int touche = e.getKeyCode();
		switch (touche) {
		case KeyEvent.VK_UP: nvxChoix.transDirection(Serpent.HAUT); break; 
		case KeyEvent.VK_DOWN: nvxChoix.transDirection(Serpent.BAS); break; 
		case KeyEvent.VK_RIGHT: nvxChoix.transDirection(Serpent.DROITE); break; 
		case KeyEvent.VK_LEFT: nvxChoix.transDirection(Serpent.GAUCHE); break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Stub de la méthode généré automatiquement
		
	}

}