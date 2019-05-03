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
		int answer = getIntFromMenu(QuestionUser);
		switch(answer) {

		case 1:
			showMenu();
			break;
		case 2:
			addPizza();
			break;
		case 3:
			updatePizza();
			break;
		case 4:
			deletePizza();
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
	
	private static void showMenu() {
		// gestion du cas menu vide
			// parce que le menu est initialisé mais quand même
		if(menu != null) {
			showText("Liste des pizzas");
			for(Pizza pizza: menu) {
				String text = pizza.code + " -> " + pizza.libelle + " (" + pizza.prix + " €) ";
				showText(text);
			}
		} else {
			showText("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
		}

	}
 	
	private static void addPizza() {
		showText("Ajout d'une nouvelle pizza");
		Pizza newPizza = getInformationPizza();
		
		String message = checkInformationPizza(newPizza,true,"add");
		if(!message.equals("")) {
			showText(message);
			addPizza();
			return;
		}
		menu = addPizzaMenu(newPizza);
		
	}
	
	private static void updatePizza() {
		showText("Mise à jour d’une pizza");
		
		showText("Veuillez choisir le code de la pizza à modifier");
		String code = getCode();
		//Vérification de l'existance de la pizza
		String[] messages = checkCode(code);
		String message = messages[0];
		int index = Integer.parseInt(messages[1]);
		
		if(!message.equals("")) {
			showText(message);
			updatePizza();
			return;
		}
		applyUpdatePizza(index);

	}
	
	private static void deletePizza() {
		showText("Suppression d’une pizza");

		showText("Veuillez choisir le code de la pizza à supprimer");

		String code = getCode();
		//Vérification de l'existance de la pizza
		String[] messages = checkCode(code);
		String message = messages[0];
		int index = Integer.parseInt(messages[1]);
		
		if(!message.equals("")) {
			showText(message);
			deletePizza();
			return;
		}
		menu = applyDeletePizza(index);
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

	private static void showIntroduction() {
		showText("***** Pizzeria Administration *****");
	}

	private static void addPizzaMenuWithId(Integer id,String code, String libelle, double prix) {
		//instancier une pizza
		Pizza pizza = new Pizza(code,libelle,prix);
		menu = addPizzaMenu(pizza);
	}

	private static Pizza[] addPizzaMenu( Pizza pizza) {

		Pizza[] menuTemporary;
		if(menu != null) {
			menuTemporary = new Pizza[menu.length + 1];
			if(menu.length != 0) {
				for (int i = 0; i < menu.length; i++) {
					menuTemporary[i] = menu[i];
				}
			}
			menuTemporary[menu.length] = pizza;
		} else {
			menuTemporary = new Pizza[1];
			menuTemporary[0] = pizza;
		}
		return menuTemporary;
	}
	
	private static void applyUpdatePizza(int index) {

		Pizza updatedPizza = getInformationPizza();
		String message = "";
		
		updatedPizza.id = menu[index].id;
		menu[index] = updatedPizza;
		message = checkInformationPizza(updatedPizza,false,"update");
		if(!message.equals("")) {
			showText(message);
			applyUpdatePizza(index);
		}
	}
	
	private static String checkInformationPizza(Pizza pizza, boolean unicity, String methode) {
		
		String error = "";
		error += checkFormatInformationPizza(pizza);

		if(unicity) {
			error += checkUnicityInformationPizza(pizza,methode);
		}
		
		return error;
	}
	
	private static String[] checkCode(String code) {
		String index = "0";
		String message = "Le code saisi semble ne correspondre a aucune pizza, pouvez vous réessayer ?";

		for (int i = 0; i < menu.length; i++ ) {
			if(menu[i].code.equals(code)) {
				message = "";
				index = Integer.toString(i);
				break;
			}
		}
		String[] messages = {message,index};
		return messages;
	}
	
	private static String checkFormatInformationPizza(Pizza pizza) {
		String error = "";
		if(pizza.code.equals("")) {
			error += "\r\nLe format du code "+ pizza.code +" est invalide";
		}
		if(pizza.libelle.equals("")) {
			error += "\r\nLe format du libelle " + pizza.libelle + " est invalide";
		}
		if(isNegative(pizza.prix)) {
			error += "\r\nLe format du prix est invalide";
		}
		return error;
	}
	
	private static String checkUnicityInformationPizza(Pizza pizza,String methode) {
		String error = "";
		for (Pizza pizzaSaved : menu) {
			if(pizzaSaved.code.equals(pizza.code)) {
				error += "\r\n Ce code a déjà été utilisé";
			}
			if(pizzaSaved.libelle.equals(pizza.libelle)) {
				error += "\r\n Ce libelle a déjà été utilisé";
			}
			if(methode.equals("add") && pizzaSaved.id == pizza.id) {
					pizza.id ++;
			}
		}
		return error;
	}


	private static Pizza[] applyDeletePizza(Integer index) {
		Pizza[] menuTemporary = null;
		if(menu.length == 1) {
			//todo delete menu;
			menuTemporary = null;
		} else if(menu != null ) {
			menuTemporary = new Pizza[menu.length - 1];
				for (int i = 0; i < menu.length; i++) {
					if(i<index) {
						menuTemporary[i] = menu[i];
					} else if(i>index) {
						menuTemporary[i] = menu[i+1];
					}					
				}
		}
		return menuTemporary;
		
	}

	public static boolean isNegative(double d) {
	     return Double.compare(d, 0.0) < 0;
	}

	private static Pizza getInformationPizza() {

		showText("Veuillez saisir le code :");
		String code = getCode();

		showText("Veuillez saisir le libelle (sans espace au possible) :");
		String libelle = getLibelle();
		
		showText("Veuillez saisir le prix :");
		double prix = getPrice();
		
		return new Pizza(code,libelle,prix);
	}
	
	private static String getCode() {
		String code = getStringFromMenu(questionUser);
		return code;
	}

	private static String getLibelle() {
		String libelle = getStringFromMenu(questionUser);
		return libelle;
	}

	private static Double getPrice() {
		Double prix = getDoubleFromMenu(questionUser);
		return prix;
	}
	
	private static int getIntFromMenu(Scanner QuestionUser) {

		String input = QuestionUser.nextLine();
        int number = 0;
        try {
            number = Integer.parseInt(input);
            return number;
        } catch (Exception e) {
            return 0;
        }
	}

	private static String getStringFromMenu(Scanner QuestionUser) {
		try {
			//todo : attention injection ?
			return (String) questionUser.next();
		} catch(Exception e)  {
			// Gestion des cas où l'utilisateur ne rentre pas un text au bon format
			return "";
		}
	}

	private static Double getDoubleFromMenu(Scanner QuestionUser) {
		String input = QuestionUser.next();
	    double number = 0;
	    try {
	        number = Double.parseDouble(input);
	        return number;
	    } catch (Exception e) {
	        return -1.00;
	    }
	}
	
	private static void showMenuOptions() {
		showText("1. Lister les pizzas \r\n2. Ajouter une nouvelle pizza \r\n3. Mettre à jour une pizza \r\n4. Supprimer une pizza \r\n99. Sortir");
	}
	
	private static void showText(String texte) {
		System.out.println(texte);
	}


}
