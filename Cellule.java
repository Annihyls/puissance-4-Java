package puissance4;

public class Cellule{
	private Couleur couleur;
	private GrilleJeu grille;
	private Cord position;
	private Cellule haut;
	private Cellule bas;
	private Cellule gauche;
	private Cellule droite;
	
	
	public Cellule(Couleur c, GrilleJeu grille){
		this.couleur = c;
		this.position = new Cord(0,0);
		this.grille = grille;
	}
	
	public Couleur getCouleur(){
		return couleur;
	}
	
	public String getNameCouleur(){
		return couleur.name();
	}
	
	public void setPosition(int ligne, int colonne){
		this.position.setX(ligne);
		this.position.setY(colonne);
		this.haut = grille.getCelluleFromGrille(ligne, colonne + 1);
		this.bas = grille.getCelluleFromGrille(ligne, colonne - 1);
		this.gauche = grille.getCelluleFromGrille(ligne - 1, colonne);
		this.droite = grille.getCelluleFromGrille(ligne + 1, colonne);
	}
/*--------------------------------GETTER HAUT BAS GAUCHE DROITE----------------------------------*/
	public Cellule getHaut(){
		return this.haut;
	}
	
	public Cellule getBas(){
		return this.bas;
	}
	
	public Cellule getGauche(){
		return this.gauche;
	}
	
	public Cellule getDroite(){
		return this.droite;
	}
/*------------------------------------------------------------------------------------------*/

	public Couleur setCouleur(Couleur c){		//permet de setter la couleur
		if(c == Couleur.ROUGE){
			this.couleur = Couleur.ROUGE;
			return this.couleur;
		}
		else if(c == Couleur.JAUNE){
			this.couleur = Couleur.JAUNE;
			return this.couleur;
		}
		else{
			this.couleur = Couleur.LIBRE;
			return this.couleur;
		}
	}
	
	public String toString(){
		if(this.getNameCouleur() == "ROUGE" || this.getNameCouleur() == "JAUNE"){
			return "●";
		}
		else{
			return " ";
		}	
	}

	public boolean check(){
		int compteur = 0;
		Cellule celluleEnCours = this;
	/*-----------------------------------------------CHECK HORIZONTAL-----------------------------------------------*/	
		for(int i=0; i<4; i++){
			if((celluleEnCours.getBas() == null) || (celluleEnCours.getBas().getCouleur() != this.getCouleur())){
				break;
			}
			else{
				celluleEnCours = celluleEnCours.getBas();
				compteur++;
				if(compteur == 3){
					return true;
				}
			}
		}

		celluleEnCours = this;

		for(int i=0; i<4; i++){
			if((celluleEnCours.getHaut() == null) || (celluleEnCours.getHaut().getCouleur() != this.getCouleur())){
				break;
			}
			else{
				celluleEnCours = celluleEnCours.getHaut();
				compteur++;
				if(compteur == 3){
					return true;
				}
			}
		}
	/*------------------------------------------------------------------------------------------------------------*/
		compteur = 0;
		celluleEnCours = this;
	/*-----------------------------------------------CHECK VERTICAL-----------------------------------------------*/
		for(int i=0; i<4; i++){
			if((celluleEnCours.getGauche() == null) || (celluleEnCours.getGauche().getCouleur() != this.getCouleur())){
				break;
			}
			else{
				celluleEnCours = celluleEnCours.getGauche();
				compteur++;
				if(compteur == 3){
					return true;
				}
			}
		}

		celluleEnCours = this;
		
		for(int i=0; i<4; i++){
			if((celluleEnCours.getDroite() == null) || (celluleEnCours.getDroite().getCouleur() != this.getCouleur())){
				break;
			}
			else{
				celluleEnCours = celluleEnCours.getDroite();
				compteur++;
				if(compteur == 3){
					return true;
				}
			}
		}
	/*------------------------------------------------------------------------------------------------------------*/
		compteur = 0;
		celluleEnCours = this;
	/*-----------------------------------------------CHECK SLASH-----------------------------------------------*/
		for(int i=0; i<4; i++){
			if((celluleEnCours.getHaut() == null || celluleEnCours.getDroite() == null) || (celluleEnCours.getHaut().getDroite().getCouleur() != this.getCouleur())){
				break;
			}
			else{
				celluleEnCours = celluleEnCours.getHaut().getDroite();
				compteur++;
				if(compteur == 3){
					return true;
				}
			}
		}

		celluleEnCours = this;
		
		for(int i=0; i<4; i++){
			if((celluleEnCours.getBas() == null || celluleEnCours.getGauche() == null) || (celluleEnCours.getBas().getGauche().getCouleur() != this.getCouleur())){
				break;
			}
			else{
				celluleEnCours = celluleEnCours.getBas().getGauche();
				compteur++;
				if(compteur == 3){
					return true;
				}
			}
		}
	/*------------------------------------------------------------------------------------------------------------*/
		compteur = 0;
		celluleEnCours = this;
	/*-----------------------------------------------CHECK ANTISLASH-----------------------------------------------*/
		for(int i=0; i<4; i++){
			if((celluleEnCours.getHaut() == null || celluleEnCours.getGauche() == null) || (celluleEnCours.getHaut().getGauche().getCouleur() != this.getCouleur())){
				break;
			}
			else{
				celluleEnCours = celluleEnCours.getHaut().getGauche();
				compteur++;
				if(compteur == 3){
					return true;
				}
			}
		}

		celluleEnCours = this;
		
		for(int i=0; i<4; i++){
			if((celluleEnCours.getBas() == null || celluleEnCours.getDroite() == null) || (celluleEnCours.getBas().getDroite().getCouleur() != this.getCouleur())){
				break;
			}
			else{
				celluleEnCours = celluleEnCours.getBas().getDroite();
				compteur++;
				if(compteur == 3){
					return true;
				}
			}
		}
/*------------------------------------------------------------------------------------------------------------*/
		return false;
	}
}