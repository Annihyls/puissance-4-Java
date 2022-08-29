package puissance4;

import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class Jeu{
	private Joueur joueur1;
	private Joueur joueur2;
	private GrilleJeu grille;
	private int coupPourJoueur = 1;
	private int choixCouleur = 0;
	private int choixJoueur = 0;
	private String nomJ1;
	private String nomJ2;

	public Jeu(){
		this.grille = new GrilleJeu();
	}


	private void initJeu(){
		Scanner scanner = new Scanner(System.in);
		Couleur rouge = Couleur.ROUGE;
		Couleur jaune = Couleur.JAUNE;
		
		while(choixJoueur != 1 && choixJoueur != 2){
			System.out.print("JOUER CONTRE UN HUMAIN OU UN ORDI ? \n1 = humain\n2 = ordi\nVotre choix:");
			this.choixJoueur = scanner.nextInt();

			if(choixJoueur == 1){
				System.out.print("Joueur 1, veuillez taper votre nom :");
				scanner.nextLine();
				this.nomJ1 = scanner.nextLine();

				System.out.print("Joueur 2, veuillez taper votre nom :");
				this.nomJ2 = scanner.nextLine();

				while(choixCouleur != 1 && choixCouleur != 2){
					System.out.print("\nJoueur 1, veuillez choisir votre couleur :\n1 = ROUGE\n2 = JAUNE\nVotre choix:");
					choixCouleur = scanner.nextInt();
					if(choixCouleur == 1){
						this.joueur1 = new Humain(nomJ1, rouge);
						this.joueur2 = new Humain(nomJ2, jaune);
					}
					else if(choixCouleur == 2){
						this.joueur1 = new Humain(nomJ1, jaune);
						this.joueur2 = new Humain(nomJ2, rouge);
					}
					else{
						System.out.println("Veuillez choisir entre rouge et jaune !");
					}
				}
			}
			else if(choixJoueur == 2){
				System.out.print("Joueur 1, veuillez taper votre nom :");
				scanner.nextLine();
				this.nomJ1 = scanner.nextLine();

				while(choixCouleur != 1 && choixCouleur != 2){
					System.out.print("\nJoueur 1, veuillez choisir votre couleur :\n1 = ROUGE\n2 = JAUNE\nVotre choix:");
					choixCouleur = scanner.nextInt();
					if(choixCouleur == 1){
						this.joueur1 = new Humain(nomJ1, rouge);
						this.joueur2 = new Ordi("ORDI", jaune);	
					}
					else if(choixCouleur == 2){
						this.joueur1 = new Humain(nomJ1, jaune);
						this.joueur2 = new Ordi("ORDI", rouge);
					}
					else{
						System.out.println("Veuillez choisir entre rouge et jaune !");
					}
				}
				this.nomJ2 = "ORDI";
			}
			else{
				System.out.println("Veuillez choisir entre jouer contre un humain ou jouer contre un ordi !");
			}
		}
	}


	private void game(){
		int over = 0;
		System.out.println(grille);

		while (over != 2 && over !=100){
			over = 0;
			if(coupPourJoueur == 1){
				while(over == 0){
					if(grille.verifGrille() == false){
						over = joueur1.jouerCoup(over, grille);
						if(over == 100){
							break;
						}
						coupPourJoueur = 2;
					}
					else{
						System.out.println("La grille est pleine ! MATCH NUL !");
						over = 2;
						break;
					}
				}
				System.out.println(grille);
			}

			if(over == 2 || over == 100){
				break;
			}

			if(coupPourJoueur == 2){
				over = 0;
				while(over == 0){
					if(grille.verifGrille() == false){
						over = joueur2.jouerCoup(over, grille);
						if(over == 100) {
							break;
						}
						coupPourJoueur = 1;
					}
					else{
						System.out.println("La grille est pleine ! MATCH NUL !");
						over = 2;
						break;
					}
				}
				System.out.println(grille);
			}
		}
		if(over == 2){
			System.out.println("Partie terminée !");
		}
		else{
			System.out.println("Partie quitté temporairement !");
			sauvegarde();
			pause();
		}
	}


	private void sauvegarde(){
		File file = new File("save.txt");

		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
			String a;

			for(int i=GrilleJeu.COLONNE-1; i>=0; i--){
				for(int j=0; j<GrilleJeu.LIGNE; j++){
					a = grille.preSauvegarde(j, i);
					bufferedWriter.write(a);
					bufferedWriter.newLine();
				}
			}

			bufferedWriter.write(String.valueOf(choixJoueur));
			bufferedWriter.newLine();
			bufferedWriter.write(String.valueOf(choixJoueur));
			bufferedWriter.newLine();
			bufferedWriter.write(nomJ1);
			bufferedWriter.newLine();
			bufferedWriter.write(nomJ2);
			bufferedWriter.newLine();
			bufferedWriter.write(String.valueOf(coupPourJoueur));

		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("SAUVEGARDE REUSSI !");
	}
	

	
	private void chargement(){
		File file = new File("save.txt");
		BufferedReader bufferedReader = null;
		int verif = 0;

		try{ 
			bufferedReader = new BufferedReader(new FileReader(file));
			String line;

			for(int i=GrilleJeu.COLONNE-1; i>=0; i--){
				for(int j=0; j<GrilleJeu.LIGNE; j++){
					line = bufferedReader.readLine();
					grille.preChargement(j, i, line);

				}
			}

			line = bufferedReader.readLine();

			if(line.compareTo("1") == 0) {
				line = bufferedReader.readLine();
				if(line.compareTo("1") == 0) {
					this.nomJ1 = bufferedReader.readLine();
					this.nomJ2 = bufferedReader.readLine();
					this.joueur1 = new Humain(nomJ1, Couleur.ROUGE);
					this.joueur2 = new Humain(nomJ2, Couleur.JAUNE);
				}
				else {
					this.nomJ1 = bufferedReader.readLine();
					this.nomJ2 = bufferedReader.readLine();
					this.joueur1 = new Humain(nomJ1, Couleur.JAUNE);
					this.joueur2 = new Humain(nomJ2, Couleur.ROUGE);
				}
			}
			else {
				line = bufferedReader.readLine();
				if(line.compareTo("1") == 0) {
					this.nomJ1 = bufferedReader.readLine();
					this.nomJ2 = bufferedReader.readLine();
					this.joueur1 = new Humain(nomJ1, Couleur.ROUGE);
					this.joueur2 = new Ordi(nomJ2, Couleur.JAUNE);	
				}
				else {
					this.nomJ1 = bufferedReader.readLine();
					this.nomJ2 = bufferedReader.readLine();
					this.joueur1 = new Humain(nomJ1, Couleur.JAUNE);
					this.joueur2 = new Ordi(nomJ2,  Couleur.ROUGE);	
				}
			}

			this.coupPourJoueur = Integer.parseInt(bufferedReader.readLine());
			System.out.println(coupPourJoueur);

		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

		try{
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=GrilleJeu.COLONNE-1; i>=0; i--){
			for(int j=0; j<GrilleJeu.LIGNE; j++){
				grille.preSetChargement(j, i);
			}
		}

		System.out.println("CHARGEMENT REUSSI !");
	}


	private void pause(){
		try{
			Thread.sleep(2000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args){
		Jeu j = new Jeu();
		int o = 0;
		Scanner partie = new Scanner(System.in);

		
		while(true){
			j.grille.initGrille();
			o = 0;

			while(o != 1 && o != 2 && o!= 3){
				System.out.print("1 = Nouvelle partie\n2 = Charger sauvegarde\n3 = Quitter\nVotre choix:");
				o = partie.nextInt();
				if (o == 2){
					j.chargement();
					j.game();
				}
				else if (o == 1){
					j.initJeu();
					j.game();
				}
			}
			if (o == 3){
				System.out.println("Fermeture du programme...\n");
				j.pause();
				break;
			}
		}
	}
}