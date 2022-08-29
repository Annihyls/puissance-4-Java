package puissance4;

import java.util.Scanner;

public class Humain extends Joueur{
	
	public Humain(String name, Couleur couleur){
		super(name, couleur);
	}
	
	@Override
	public int jouerCoup(int over, GrilleJeu grille){
		Scanner scanner = new Scanner(System.in);
		int coup = 10;
		while(coup < 0 || coup > 6){
			System.out.print(this.getName()+", veuillez choisir un entier entre 1 et 7 pour jouer, ou tapez 100 pour quitter:");
			coup = scanner.nextInt();
			
			if(coup == 100){
				return 100;
			}
			coup--;
		}
		over = grille.ajoute(coup, this.getCouleur(), this);
		return over;
	}
}