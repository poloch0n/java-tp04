package fr.pizzeria.model;

public class Pizza {
	int id;
	String code;
	String libelle;
	double prix;
	private int compteur = 0;

	Pizza(String code, String libelle, double prix){
		compteur++;
		this.id = compteur;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}
	
	Pizza(Integer id,String code, String libelle, double prix){
		//to think doublons
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}
}
