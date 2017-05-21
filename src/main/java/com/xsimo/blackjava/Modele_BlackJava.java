package com.xsimo.blackjava;


public class Modele_BlackJava {

	public BlackJava vue;
	public Joueur j;
	public Croupier c;
	public Paquet p;
	public Modele_BlackJava(BlackJava vue){
		this.vue = vue;
		c = new Croupier();
		j = new Joueur();
	}
	
}
