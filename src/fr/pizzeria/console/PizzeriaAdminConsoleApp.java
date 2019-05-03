package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.model.*;

public class PizzeriaAdminConsoleApp {

	private static Scanner questionUser;
	static Pizza[] menu;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		questionUser = new Scanner(System.in);
		
		initialisationMenu();
		
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
	
	private static void initialisationMenu() {
		addPizzaMenuWithId(0, "PEP","Pépéroni", 12.50);
		addPizzaMenuWithId(1, "MAR","Margherita", 14.00);
		addPizzaMenuWithId(2, "REI","La Reine", 11.50);
		addPizzaMenuWithId(3, "FRO","La 4 fromages", 12.00);
		addPizzaMenuWithId(4, "CAN","La cannibale", 12.50);
		addPizzaMenuWithId(5, "SAV","La savoyarde", 13.00);
		addPizzaMenuWithId(6, "ORI","L'orientale", 13.50);
		addPizzaMenuWithId(7, "IND","L'indienne", 14.00);
		
	}
	
	private static void addPizzaMenu(String code, String libelle, double prix) {
		//instancier une pizza
		Pizza pizza = new Pizza(code,libelle,prix);
		menu = ajustPizzaMenu(menu, pizza);
	}
	
	private static void addPizzaMenuWithId(Integer id,String code, String libelle, double prix) {
		//instancier une pizza
		Pizza pizza = new Pizza(code,libelle,prix);
		menu = ajustPizzaMenu(menu, pizza);
	}

	private static Pizza[] ajustPizzaMenu(Pizza[] menuInit, Pizza pizza) {
		//todo : think again about this fonction
			// check doublons id
			// check double libelle
			// check doublons code
			// principe
		Pizza[] menuTemporary;
		if(menuInit != null) {
			menuTemporary = new Pizza[menuInit.length + 1];
			if(menuInit.length != 0) {
				for (int i = 0; i < menuInit.length; i++) {
					menuTemporary[i] = menuInit[i];
				}
			}
			menuTemporary[menuInit.length] = pizza;
		} else {
			menuTemporary = new Pizza[1];
			menuTemporary[0] = pizza;
		}
		return menuTemporary;
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
