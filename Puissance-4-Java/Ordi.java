package puissance4;

import java.util.Random;

public class Ordi extends Joueur{
	private Random random;
	
	public Ordi(String name, Couleur couleur){
		super(name, couleur);
		random = new Random();
	}
	
	@Override
	public int jouerCoup(int over, GrilleJeu grille){
		int coup;
		coup = this.random.nextInt(6);
		coup++;
		System.out.println(this.getName()+" a joué sur la colonne " +coup);
		coup--;
		over = grille.ajoute(coup, this.getCouleur(), this);
		return over;
	}
}