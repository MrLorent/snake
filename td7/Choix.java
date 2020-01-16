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

//Implément la class en ActionListener afin de pouvoir récupérer les saisies des champs du formulaire
public class Choix extends JPanel implements ActionListener {
	private JComboBox nbNourriture, tailleGrille;
	private Jeu appli;
	private ZoneDeJeu laZoneDeJeu;
	
	public Choix(Jeu application) {
		//On récupèrer la fenêtre application dans laquelle on se trouve
		appli = application;
		//On défini un background
		setBackground(Color.WHITE);
		//On définit la méthode d'affichage
		setLayout(new BorderLayout());
		
		//On créer un nouveau panneau plus petit, enfant de panChoix, qui contiendra les champs à renseigner
		JPanel petitPanneau = new JPanel();
		add(petitPanneau, BorderLayout.CENTER);
		petitPanneau.setLayout(new GridLayout(2,2));
		
		//On créer un label afin d'indiquer en quoi consiste le formulaire de choix
		JLabel choixParametres = new JLabel("Choix des paramètres");
		choixParametres.setHorizontalAlignment(JLabel.CENTER);
		choixParametres.setPreferredSize(new Dimension(50,20));
		add(choixParametres, BorderLayout.NORTH);
		
		//On créer un choix de quantité de nourriture avec un label et une liste de choix via l'objet JComboBox
		JLabel qteNourriture = new JLabel("Quantité de nourriture");
		petitPanneau.add(qteNourriture);
		String[] nourriture = {"5","10","15","20","25","30"};
		nbNourriture = new JComboBox(nourriture);
		petitPanneau.add(nbNourriture);
		
		//On créer un autre choix pour la taille de la grille avec un label et une liste de choix via l'objet JComboBox
		JLabel dimGrille = new JLabel("Dimension de la grille");
		petitPanneau.add(dimGrille);
		String[] dim = {"10x10","20x20","30x30","40x40","50x50"};
		tailleGrille = new JComboBox(dim);
		petitPanneau.add(tailleGrille);
		
		//On créer le bouton valider de manière à pouvoir envoyer les saisies du formulaire
		JButton valider = new JButton("Valider");
		add(valider, BorderLayout.SOUTH);
		
		//On place un écouteur sur le bouton valider
		valider.addActionListener(this);
	}
	
	//Fonction permettant de transmettre les interactions récupérées dans la fenêtre principale "jeu"
	public void transDirection(int nvlleDirection) {
		laZoneDeJeu.transDirection(nvlleDirection);
	}
	
	//Fonction permettant de récupérer les saisies des champs du formulaire lorsque le formulaire est validé
	public void actionPerformed(ActionEvent e) {
		//On récupère les paramètres saisies
		String quantite = (String)nbNourriture.getSelectedItem();
		String taille = (String)tailleGrille.getSelectedItem();
		
		//On transforme les string de quantité de nourriture en int
		int qte = Integer.parseInt(quantite);
		//On récupère les dimension choisie en enlevant le x
		String[] dimension = taille.split("x");
		int largeur = Integer.parseInt(dimension[0]);
		int hauteur = Integer.parseInt(dimension[1]);
		
		//On enlève le panneau de choix de la fenêtre principale
		appli.getContentPane().removeAll();
		//On définit la taille de la fenêtre principale sur celle de la grille de jeu
		appli.getContentPane().setPreferredSize(new Dimension(largeur*20, hauteur*20));
		//On appel le constructeur de zone de jeu avec les paramètres demandés
		laZoneDeJeu = new ZoneDeJeu(qte, largeur, hauteur);
		//On ajoute la zone de jeu à la fenêtre principale
		appli.getContentPane().add(laZoneDeJeu);
		//On ajoute l'écouteur qui permettera de récupérer les interractions claviers sur la fenêtre principale
		appli.getContentPane().addKeyListener(appli);
		//On demande à l'ordinateur toute son attention
		appli.getContentPane().requestFocusInWindow();
		//On demande à la fenêtre principale d'afficher ses bordures etc autour de la taille demandée
		appli.pack();
		appli.validate();
	}
}











