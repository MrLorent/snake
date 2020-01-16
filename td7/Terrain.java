package td7;

import java.awt.Color;
import java.awt.Graphics;

public class Terrain {
	private boolean[][] surfaceTerrain;
	private int largeurTerrain,hauteurTerrain,aleaLargeur,aleaHauteur;
	private static int compteurNourriture;
	
	//CONSTRUCTEURS
	public Terrain(int qteNourriture, int largeur, int hauteur) {
		compteurNourriture = qteNourriture;
		largeurTerrain = largeur;
		hauteurTerrain = hauteur;
		surfaceTerrain = new boolean[largeur][hauteur];
		for(int i=0; i<largeur; i++) {
			for(int j=0; j<hauteur; j++) {
				surfaceTerrain[i][j] = false;
			}
		}
		
		for(int i=0;i<qteNourriture;i++) {
			do {
				aleaLargeur = (int) (Math.random() * largeur);
				aleaHauteur = (int) (Math.random() * hauteur);
			}while(surfaceTerrain[aleaLargeur][aleaHauteur] == true);
			surfaceTerrain[aleaLargeur][aleaHauteur] = true;
		}
	}
	
	//ACCESSEURS
	//de consultation
	public boolean contientNourriture(int x, int y) {
		return surfaceTerrain[x][y];
	}
	
	//de modification
	public void supprimerNourriture(int x, int y) {
		surfaceTerrain[x][y] = false;
		compteurNourriture--;
	}
	
	//MÉTHODES
	
	public void seDessine(Graphics g) {
		for(int i=0; i<largeurTerrain; i++) {
			for(int j=0; j<hauteurTerrain; j++) {
				if(surfaceTerrain[i][j]) {
					g.setColor(Color.RED);
					g.fillOval(i*20,j*20,20,20);
				}
			}
		}
	}
}
