package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	private static Scanner questionUser;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		questionUser = new Scanner(System.in);
		
		showIntroduction();
		run(questionUser);
	}
	
	private static void run(Scanner QuestionUser) {

		showMenuOptions();
		int answer = getNumberFromMenu(QuestionUser);
		switch(answer) {

		case 1:
			showText("Liste des pizzas");
			// afficher les pizzas
				//case empty
			break;
		case 2:
			showText("Ajout d'une nouvelle pizza");
			break;
		case 3:
			showText("Mise à jour d’une pizza");
			break;
		case 4:
			showText("Suppression d’une pizza");
			break;
		case 99:
			showText("Aurevoir☹ ");
			System.exit(0);
			// Arreter 
			break;
		default:
			showText("Veuillez choisir une option valide");
		    break;
		}
		
		run(QuestionUser);
	}

	private static void showIntroduction() {
		showText("***** Pizzeria Administration *****");
	}
	
	private static void showMenuOptions() {
		showText("1. Lister les pizzas \r\n2. Ajouter une nouvelle pizza \r\n3. Mettre à jour une pizza \r\n4. Supprimer une pizza \r\n99. Sortir");
	}
	
	private static void showText(String texte) {
		System.out.println(texte);
	}

	private static int getNumberFromMenu(Scanner QuestionUser) {
		try {
			return questionUser.nextInt();
		} catch(Exception e)  {
			// Gestion des cas où l'utilisateur ne rentre pas un nombre
			return 0;
		}
	}

}
