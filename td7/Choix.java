package td7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Impl�ment la class en ActionListener afin de pouvoir r�cup�rer les saisies des champs du formulaire
public class Choix extends JPanel implements ActionListener {
	private JComboBox nbNourriture, tailleGrille;
	private Jeu appli;
	private ZoneDeJeu laZoneDeJeu;
	
	public Choix(Jeu application) {
		//On r�cup�rer la fen�tre application dans laquelle on se trouve
		appli = application;
		//On d�fini un background
		setBackground(Color.WHITE);
		//On d�finit la m�thode d'affichage
		setLayout(new BorderLayout());
		
		//On cr�er un nouveau panneau plus petit, enfant de panChoix, qui contiendra les champs � renseigner
		JPanel petitPanneau = new JPanel();
		add(petitPanneau, BorderLayout.CENTER);
		petitPanneau.setLayout(new GridLayout(2,2));
		
		//On cr�er un label afin d'indiquer en quoi consiste le formulaire de choix
		JLabel choixParametres = new JLabel("Choix des param�tres");
		choixParametres.setHorizontalAlignment(JLabel.CENTER);
		choixParametres.setPreferredSize(new Dimension(50,20));
		add(choixParametres, BorderLayout.NORTH);
		
		//On cr�er un choix de quantit� de nourriture avec un label et une liste de choix via l'objet JComboBox
		JLabel qteNourriture = new JLabel("Quantit� de nourriture");
		petitPanneau.add(qteNourriture);
		String[] nourriture = {"5","10","15","20","25","30"};
		nbNourriture = new JComboBox(nourriture);
		petitPanneau.add(nbNourriture);
		
		//On cr�er un autre choix pour la taille de la grille avec un label et une liste de choix via l'objet JComboBox
		JLabel dimGrille = new JLabel("Dimension de la grille");
		petitPanneau.add(dimGrille);
		String[] dim = {"10x10","20x20","30x30","40x40","50x50"};
		tailleGrille = new JComboBox(dim);
		petitPanneau.add(tailleGrille);
		
		//On cr�er le bouton valider de mani�re � pouvoir envoyer les saisies du formulaire
		JButton valider = new JButton("Valider");
		add(valider, BorderLayout.SOUTH);
		
		//On place un �couteur sur le bouton valider
		valider.addActionListener(this);
	}
	
	//Fonction permettant de transmettre les interactions r�cup�r�es dans la fen�tre principale "jeu"
	public void transDirection(int nvlleDirection) {
		laZoneDeJeu.transDirection(nvlleDirection);
	}
	
	//Fonction permettant de r�cup�rer les saisies des champs du formulaire lorsque le formulaire est valid�
	public void actionPerformed(ActionEvent e) {
		//On r�cup�re les param�tres saisies
		String quantite = (String)nbNourriture.getSelectedItem();
		String taille = (String)tailleGrille.getSelectedItem();
		
		//On transforme les string de quantit� de nourriture en int
		int qte = Integer.parseInt(quantite);
		//On r�cup�re les dimension choisie en enlevant le x
		String[] dimension = taille.split("x");
		int largeur = Integer.parseInt(dimension[0]);
		int hauteur = Integer.parseInt(dimension[1]);
		
		//On enl�ve le panneau de choix de la fen�tre principale
		appli.getContentPane().removeAll();
		//On d�finit la taille de la fen�tre principale sur celle de la grille de jeu
		appli.getContentPane().setPreferredSize(new Dimension(largeur*20, hauteur*20));
		//On appel le constructeur de zone de jeu avec les param�tres demand�s
		laZoneDeJeu = new ZoneDeJeu(qte, largeur, hauteur);
		//On ajoute la zone de jeu � la fen�tre principale
		appli.getContentPane().add(laZoneDeJeu);
		//On ajoute l'�couteur qui permettera de r�cup�rer les interractions claviers sur la fen�tre principale
		appli.getContentPane().addKeyListener(appli);
		//On demande � l'ordinateur toute son attention
		appli.getContentPane().requestFocusInWindow();
		//On demande � la fen�tre principale d'afficher ses bordures etc autour de la taille demand�e
		appli.pack();
		appli.validate();
	}
}











