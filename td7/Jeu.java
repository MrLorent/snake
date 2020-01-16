package td7;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

//On d�clare la classe comme KeyListener pour pouvoir r�cup�rer les interractions clavier
public class Jeu extends JFrame implements KeyListener {
	private Choix nvxChoix;
	
	public Jeu() {
		//On g�re l'ouverture/fermeture de la fen�tre		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//On lui donne un titre
		setTitle("Application TD7");
		//On lui donne une taille bloqu�e
		setSize (new Dimension (400, 300));
		
		//On appel le constructeur de panneau de choix
		Choix panChoix = new Choix(this);
		//On l'ajoute � la fen�tre principale "jeu"
		getContentPane().add(panChoix);
		
		//On cr�er un nouveau choix afin de passer les interactions claviers aux autre fen�tres
		nvxChoix = new Choix(this);
		//On l'ajoute � la fen�tre principale "Jeu"
		getContentPane().add(nvxChoix);
		//On affiche les composants graphiques
		setVisible(true);
		
	}
	
	public static void main (String[] args) {
		//Le main sert seulement � lancer le constructeur de l'application jeu
		new Jeu();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// On met des �couteurs sur les touches qui nous int�resses afin de g�rer les interractions clavier
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
		// TODO Stub de la m�thode g�n�r� automatiquement
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Stub de la m�thode g�n�r� automatiquement
		
	}

}