package puissance4;

import java.util.LinkedList;
import java.util.Collection;
import java.util.StringTokenizer;

public class GrilleJeu{
	public static final String RESET = "\u001B[0m";
	public static final String JAUNE = "\u001B[33m";
	public static final String ROUGE = "\u001B[31m";
	public static final String BLEU= "\u001B[34m";
	public static final int COLONNE = 6;
	public static final int LIGNE = 7;
	private Cellule grille[][];
	

	public GrilleJeu(){
		grille = new Cellule [LIGNE][COLONNE];
	}

	public void initGrille(){
		/*JE REMPLIS LE TABLEAUX DE CELLULES LIBRES*/
		for(int i=0; i<LIGNE; i++){
			for(int j=0; j<COLONNE; j++){
				grille[i][j] =  new Cellule(Couleur.LIBRE, this);
			}
		}
		
		/*JE SET LA POSITION DES CELLULES*/
		for(int i=0; i<LIGNE; i++){
			for(int j=0; j<COLONNE; j++){
				grille[i][j].setPosition(i, j);
			}
		}
	}

	public boolean verifGrille(){
		for(int i=0; i<LIGNE; i++){
			for(int j=0; j<COLONNE; j++){
				if(grille[i][j].getCouleur() == Couleur.LIBRE){
					return false;
				}
			}
		}
		return true;
	}

	public String toString(){
		for(int i=COLONNE-1; i>=0; i--){
			for(int j=0; j<LIGNE; j++){
				if(grille[j][i].getCouleur() == Couleur.JAUNE){
					System.out.print(BLEU+"| "+RESET+JAUNE+grille[j][i]+RESET+BLEU+" |"+RESET);
				}
				else if(grille[j][i].getCouleur() == Couleur.ROUGE){
					System.out.print(BLEU+"| "+RESET+ROUGE+grille[j][i]+RESET+BLEU+" |"+RESET);
				}
				else{
					System.out.print(BLEU+"| "+RESET+grille[j][i]+BLEU+" |"+RESET);
				}
			}
			System.out.println("");
		}
		return "";
	}
	
	public Cellule getCelluleFromGrille(int ligne, int colonne){
		if(ligne >= LIGNE || ligne < 0){
			return null;
		}
		if(colonne >= COLONNE || colonne < 0){
			return null;
		}
		return grille[ligne][colonne];		
		
	}
	
	public int ajoute(int choix, Couleur c, Joueur joueur){
		int j = 0;
		for(j=0; j<COLONNE; j++){
			if(grille[choix][j].getHaut() == null){
				if(grille[choix][j].getCouleur() == Couleur.LIBRE){
					break;
				}
				System.out.println("Vous ne pouvez pas placer de jetons ici ! La colonne est pleine !");
				return 0;
			}
			
			if(grille[choix][j].getCouleur() == Couleur.LIBRE){
				break;
			}
		}
		grille[choix][j].setCouleur(c);
		if(grille[choix][j].check() == true){
			System.out.println("\nPUISSANCE 4 pour "+joueur.getName()+" !");
			return 2;
		}
		else{
			return 1;
		}
	}

	public String preSauvegarde(int i, int j){

		if(grille[i][j].getCouleur() == Couleur.ROUGE){
			return "ROUGE";
		}
		else if(grille[i][j].getCouleur() == Couleur.JAUNE){
			return "JAUNE";
		}
		else{
			return "LIBRE";
		}
	}

	public void preChargement(int i, int j, String line){
		if(line.compareTo("ROUGE") == 0){
			grille[i][j].setCouleur(Couleur.ROUGE);
		}
		else if(line.compareTo("JAUNE") == 0){
			grille[i][j].setCouleur(Couleur.JAUNE);
		}
	}

	public void preSetChargement(int i, int j){
		grille[i][j].setPosition(i, j);
	}
}