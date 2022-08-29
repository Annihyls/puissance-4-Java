package puissance4;

abstract class Joueur{
	private final String name;
	private final Couleur couleur;
	
	public Joueur(String name, Couleur couleur){
		this.name = name;
		this.couleur = couleur;
	}
	
	public String getName(){
		return name;
	}

	public Couleur getCouleur(){
		return couleur;
	}
	
	abstract int jouerCoup(int over, GrilleJeu grille);
}