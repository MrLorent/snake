package td7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class Serpent {
	//CONSTANTES
	public static final int HAUT = 0;
	public static final int DROITE = 1;
	public static final int BAS = 2;
	public static final int GAUCHE = 4;
	//VARIABLES
	private ArrayList<Dimension> leSerpent;
	private Dimension laTete;
	private int xStart, direction, xMax, yMax, laQueueX, laQueueY;
	
	//CONSTRUCTEUR
	public Serpent(int largeur, int hauteur) {
		xMax = largeur-1;
		yMax = hauteur-1;
		leSerpent = new ArrayList<Dimension>();
		xStart = (int) (Math.random()*largeur);
		leSerpent.add(new Dimension(xStart,0));
		direction = BAS;
	}
	//ACCESSEURS
	//de lecture
	
	public Dimension positionTete() {
		return leSerpent.get(0);
	}
	
	//de modification
	public void setDirection(int nvlleDirection) {
		direction = nvlleDirection;
	}
	
	//MÉTHODES
	public void seDeplace() {
		boolean caseValideX = false, caseValideY = false;
		
		Dimension laTete = leSerpent.get(0), tetePotentielle, laNouvelleTete;
		laQueueX = leSerpent.get(leSerpent.size()-1).width;
		laQueueY = leSerpent.get(leSerpent.size()-1).height;
		leSerpent.remove(leSerpent.size()-1);
		
		//On va générer une nouvelle tete jusqu'à ce que celle-ci soit dans le terrain
		do {
			//On commence par traiter la direction du serpent
			switch (direction){
				
			case HAUT:
				laNouvelleTete = new Dimension(laTete.width,laTete.height-1);
				break;
				
			case DROITE:
				laNouvelleTete = new Dimension(laTete.width+1,laTete.height);
				break;
				
			case BAS:
				laNouvelleTete = new Dimension(laTete.width,laTete.height+1);
				break;
			
			case GAUCHE:
				laNouvelleTete = new Dimension(laTete.width-1,laTete.height);
				break;
			
			default:
				laNouvelleTete = new Dimension(0,0);
			}
			
			
			//On vérifie que la nouvelle tete est bien dans le terrain
			if(laNouvelleTete.width>=0 && laNouvelleTete.width<=xMax) {
				caseValideX = true;
			}else {
				laNouvelleTete.setSize(laTete);
				if(Math.random()<0.5) {
					if(laNouvelleTete.height-1>=0) {
						direction = HAUT;
					}else {
						direction = BAS;
					}
				}else {
					if(laNouvelleTete.height+1<=yMax) {
						direction = BAS;
					}else {
						direction = HAUT;
					}
				}
			}
			if(laNouvelleTete.height>=0 && laNouvelleTete.height<=yMax) {
				caseValideY = true;
			}else {
				laNouvelleTete.setSize(laTete);
				if(Math.random()<0.5) {
					if(laNouvelleTete.width+1<=xMax) {
						direction = DROITE;
					}else {
						direction = GAUCHE;
					}
				}else {
					if(laNouvelleTete.width-1>=0) {
						direction = GAUCHE;
					}else {
						direction = DROITE;
					}
				}
			}
		}while(!(caseValideX && caseValideY));
		laTete =  laNouvelleTete;
		leSerpent.add(0, laTete);
	}
	
	
	public void sAgrandit() {
		//On remet la dernière case enlevée lors du déplacement
		leSerpent.add(new Dimension(laQueueX,laQueueY));
	}
	
	public boolean seMordLaQueue() {
		boolean seMordLaQueue = false;
		laTete = leSerpent.get(0);
		for(int i=1;i<leSerpent.size();i++) {
			if(laTete.equals(leSerpent.get(i))) {
				seMordLaQueue = true;
			}
		}
		return seMordLaQueue;
	}
	
	public void seDessine(Graphics gc) {
		for(int i=0;i<leSerpent.size();i++) {
			int x = (leSerpent.get(i).width)*20;
			int y = (leSerpent.get(i).height)*20;
			gc.setColor(Color.ORANGE);
			gc.fillRect(x, y, 18, 18);
		}
	}
}
